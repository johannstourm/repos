package com.stourm.mtgappariement.dto;

import java.util.List;

import com.stourm.mtgappariement.entities.Tournoi;
import com.stourm.mtgappariement.entities.Username;

public record TournoiInDTO(Tournoi tournoi, List<Username> lstUser) {}


