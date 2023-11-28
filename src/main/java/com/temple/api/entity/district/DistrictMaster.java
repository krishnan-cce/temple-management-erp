package com.temple.api.entity.district;

import java.io.Serializable;
import java.util.Date;

import com.temple.api.entity.state.StateMaster;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="DistrictMaster")
@NamedQueries({
	@NamedQuery(name="DistrictMaster.getByState",query="select d from DistrictMaster d where d.stateMaster = :stateMaster"),
	@NamedQuery(name = "DistrictMaster.countByDistrict", query = "select count(c) from DistrictMaster c where lower(c.district) = :district and c.stateMaster = :stateMaster and c.districtId is not null"),
	@NamedQuery(name = "DistrictMaster.countByDistrictForEdit", query = "select count(c) from DistrictMaster c where lower(c.district) = :district and c.stateMaster = :stateMaster and c.districtId <> :id"),
	@NamedQuery(name = "DistrictMaster.sortedList", query = "select d from DistrictMaster d order by d.district ASC")
	
})
public class DistrictMaster implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String GET_BY_STATE="DistrictMaster.getByState";
	public static final String COUNT_BY_DISTRICT="DistrictMaster.countByDistrict";
	public static final String GET_BY_DISTRICT_EDIT="DistrictMaster.countByDistrictForEdit";
	public static final String LIST_ALL="DistrictMaster.sortedList";
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DistrictId")
	private Long districtId;
	
	@Column(name="DistrictNo")
	private Integer districtNo;
	
	@Column(name="District",nullable=false,length=25)
	private String district;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="StateId")
	private StateMaster stateMaster;
	
	@Column(name="StateNo",nullable=false)
	private int stateNo;
	
	@Column(name="AddUid",nullable=true,length=20)
	private String addUid;
	
	@Column(name="Adddate",nullable=true)	
	private Date addDate;
	

}
