package com.clothingstore.Convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.clothingstore.DTO.ColorDTO;
import com.clothingstore.DTO.Short.ColorShortDTO;
import com.clothingstore.entity.EntityColor;

@Component
public class ColorConvert {

		public ColorDTO toDTO(EntityColor entity) {
			ColorDTO color = null;
			if(entity !=null ) {
				color = new ColorDTO();
				color.setId(entity.getId());
				color.setColorName(entity.getColorName());
				color.setIsActive(entity.getIsActive());
				color.setCreatedBy(entity.getCreatedBy());
				color.setCreatedDate(entity.getCreatedDate());
				color.setModifiedBy(entity.getModifiedBy());
				color.setModifiedDate(entity.getModifiedDate());
			}
			return color;
		}
		
		public EntityColor toEntity(ColorDTO color) {
			EntityColor entity = null;
			if(color !=null ) {
				entity = new EntityColor();
				entity.setId(color.getId());
				entity.setColorName(color.getColorName());
				if(color.getIsActive() == null) {
					entity.setIsActive(true);
				}else {
					entity.setIsActive(color.getIsActive());
				}
				entity.setCreatedBy(color.getCreatedBy());
				entity.setCreatedDate(color.getCreatedDate());
				entity.setModifiedBy(color.getModifiedBy());
				entity.setModifiedDate(color.getModifiedDate());
			}
			return entity;
		}
		
		public EntityColor colorShortToEntity(ColorShortDTO colorShort) {
			EntityColor entity = null;
			if(colorShort !=null ) {
				entity = new EntityColor();
				entity.setId(colorShort.getId());
				entity.setColorName(colorShort.getColorName());
			}
			return entity;
		}
		
		public List<EntityColor> shortsToEntities(List<ColorShortDTO> colorShorts){
			List<EntityColor> listEColors = null;
			if( colorShorts != null) {
				listEColors = new ArrayList<>();
				for(ColorShortDTO colorShort : colorShorts) {
					listEColors.add(colorShortToEntity(colorShort));
				}
			}
			return listEColors;
		}
		
		public List<ColorDTO> toListDTO(List<EntityColor> entities){
			List<ColorDTO> listDTO = null;
			if(entities != null) {
				listDTO = new ArrayList<>();
				for(EntityColor entity : entities) {
					listDTO.add(toDTO(entity));
				}
			}
			return listDTO;
		}
		
		public List<EntityColor> toEntities(List<ColorDTO> listDTO){
			List<EntityColor> entities = null;
			if(listDTO != null) {
				entities = new ArrayList<>();
				for(ColorDTO dto : listDTO) {
					entities.add(toEntity(dto));
				}
			}
			return entities;
		}
		
		public EntityColor toNewColor(EntityColor oldColor, EntityColor newColor) {
			oldColor.setColorName(newColor.getColorName());
			oldColor.setIsActive(newColor.getIsActive());
			oldColor.setCreatedBy(newColor.getCreatedBy());
			oldColor.setCreatedDate(newColor.getCreatedDate());
			oldColor.setModifiedBy(newColor.getModifiedBy());
			oldColor.setModifiedDate(newColor.getModifiedDate());
			return oldColor;
		}
}
