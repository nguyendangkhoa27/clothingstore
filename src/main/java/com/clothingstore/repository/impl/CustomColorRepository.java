package com.clothingstore.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.clothingstore.DTO.ColorDTO;
import com.clothingstore.entity.EntityColor;

@Repository
public class CustomColorRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<EntityColor> list(ColorDTO dto){
		StringBuilder sql = new StringBuilder("SELECT * FROM color WHERE id > 0");
		if(dto.getId() != null) {
			sql.append(" AND id = :id_color");
		}
		if(dto.getColorName() != null && !dto.getColorName().isEmpty()) {
			sql.append(" AND color_name ILIKE :name");
		}
		if(dto.getIsActive()!=null) {
			sql.append(" AND is_active = :active");
		}
		Query query = entityManager.createNativeQuery(sql.toString(), EntityColor.class);
		if(dto.getId() != null) {
			query.setParameter("id_color", dto.getId());
		}
		if(dto.getColorName() != null && !dto.getColorName().isEmpty()) {
			query.setParameter("name", "%"+dto.getColorName()+"%");
		}
		if(dto.getIsActive()!=null) {
			query.setParameter("active", dto.getIsActive());
		}
		List<EntityColor> listEn = query.getResultList(); 
		return listEn;
	}
}
