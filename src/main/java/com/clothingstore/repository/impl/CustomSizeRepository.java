package com.clothingstore.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clothingstore.entity.EntitySize;

@Repository
@Transactional
public class CustomSizeRepository {
		
		@Autowired
		private EntityManager entityManager;
	@SuppressWarnings("unchecked")
	public List<EntitySize> listSize(EntitySize entitySize){
		
		StringBuilder sql = new StringBuilder("SELECT * FROM size WHERE id > 0");
		if(entitySize.getId() != null) {
			sql.append(" AND id = :id_size");
		}
		if(entitySize.getNameSize() != null && !entitySize.getNameSize().isEmpty()) {
			sql.append(" AND name_size ILIKE :name");
		}
		if(entitySize.getIsActive()!=null) {
			sql.append(" AND is_active = :active");
		}
		Query query = entityManager.createNativeQuery(sql.toString(), EntitySize.class);
		if(entitySize.getId() != null) {
			query.setParameter("id_size", entitySize.getId());
		}
		if(entitySize.getNameSize() != null && !entitySize.getNameSize().isEmpty()) {
			query.setParameter("name", "%"+entitySize.getNameSize()+"%");
		}
		if(entitySize.getIsActive()!=null) {
			query.setParameter("active", entitySize.getIsActive());
		}
		return query.getResultList();
	} 
}
