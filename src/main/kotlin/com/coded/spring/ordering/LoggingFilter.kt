package com.coded.spring.ordering

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import org.slf4j.LoggerFactory

@Component
@Order(1)
class LoggingFilter(
    @Value("\${logging-level}")
    private val logLevel: String
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val cachedRequest = ContentCachingRequestWrapper(request)
        val cachedResponse = ContentCachingResponseWrapper(response)

        filterChain.doFilter(cachedRequest, cachedResponse)

        logRequest(cachedRequest)
        logResponse(cachedResponse)

        cachedResponse.copyBodyToResponse()
    }

    private fun logRequest(request: ContentCachingRequestWrapper) {
        val requestBody = String(request.contentAsByteArray)

        if (logLevel == "DEBUG") {
            logger.info("Request: method=${request.method}, uri=${request.requestURI}, body=$requestBody")
        } else {
            logger.info("Request: method=${request.method}, uri=${request.requestURI}")
        }
    }

    private fun logResponse(response: ContentCachingResponseWrapper) {
        val responseBody = String(response.contentAsByteArray)

        if (logLevel == "DEBUG") {
            logger.info("Response: status=${response.status}, body=$responseBody")
        } else {
            logger.info("Response: status=${response.status}")
        }
    }
}
