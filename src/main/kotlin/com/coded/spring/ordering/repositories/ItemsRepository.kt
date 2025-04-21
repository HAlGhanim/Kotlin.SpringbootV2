package com.coded.spring.ordering.repositories

import com.coded.spring.ordering.entities.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository


interface ItemsRepository: JpaRepository<ItemEntity, Long>
