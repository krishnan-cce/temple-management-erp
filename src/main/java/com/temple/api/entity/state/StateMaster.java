package com.temple.api.entity.state;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="StateMaster")
@NamedQueries({
	@NamedQuery(name = "StateMaster.countByState", query = "select count(c) from StateMaster c where lower(c.state) = :state and c.stateId is not null"),
	@NamedQuery(name = "StateMaster.countByStateForEdit", query = "select count(c) from StateMaster c where lower(c.state) = :state and c.stateId <> :id"),
	@NamedQuery(name = "StateMaster.sortedList", query = "select s from StateMaster s order by s.state ASC"),
	@NamedQuery(name ="StateMaster.homeState", query = "select s from StateMaster s where s.stateId = :id")
})
public class StateMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="StateId")
	private Long stateId;
	
	@Column(name="StateNo")
	private Integer stateNo;
	
	@Column(name="State",nullable=false,length=25)
	private String state;
	
	@Column(name="GSTStateNo",nullable=true)
	private Integer gstStateNo;
	
	@Column(name="BuiltIn",nullable=false)
	private boolean builtIn = false;

}
