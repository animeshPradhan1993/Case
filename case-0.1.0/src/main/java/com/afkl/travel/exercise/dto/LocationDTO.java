package com.afkl.travel.exercise.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.afkl.travel.exercise.Converters.LocationConverter;
import com.afkl.travel.exercise.model.LocationType;

@Entity
@Table(name = "location")

public class LocationDTO {

	@Id
	@Column
	private Integer id;
	@Column
	private String code;
	@Column
	@Convert(converter = LocationConverter.class)
	private LocationType type;

	@Column
	private Double latitude;
	@Column
	private Double longitude;
	@Column(name = "parentType")
	@Convert(converter = LocationConverter.class)
	private LocationType parentType;
	@Column(name = "parent_code")
	private String parentCode;


	@OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TranslationDTO> translations;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public LocationType getType() {
		return type;
	}

	public void setType(LocationType type) {
		this.type = type;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public List<TranslationDTO> getTranslations() {
		return translations;
	}

	public void setTranslations(List<TranslationDTO> translations) {
		this.translations = translations;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public LocationType getParentType() {
		return parentType;
	}

	public void setParentType(LocationType parentType) {
		this.parentType = parentType;
	}
	

}
