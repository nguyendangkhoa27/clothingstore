package com.clothingstore.Convert;

import java.util.List;

public interface abstractConvert<DTO,Entity> {
	public DTO toDTO(Entity convert);
	public Entity toEntity(DTO convert);
	public Entity newToOld(Entity old,Entity neww) ;
	public List<DTO> toDTO(List<Entity> entities);
	public List<Entity> toEntity(List<DTO> DTOs);
}
