package net.croxis.plugins.civilmineation;
public enum CityPlotType {
	RESIDENTIAL,  // The default Block Type.
    COMMERCIAL,  // Just like residential but has additional tax
    ARENA,	//Always PVP enabled.
    EMBASSY,  // For other towns to own a plot in your town.
    WILDS,//Follows wilderness protection settings, but town owned.
    LIBRARY,
    UNIVERSITY,
    MONUMENT,
    ;	
    // These are subject to change:
/*
    PUBLIC(5, "") {  // Will have it's own permission set
    },

    MINE(6, "") {  // Will have it's own permission set within a y range
    },

    HOTEL(7, "") {  // Will stack multiple y-ranges and function like a micro town
    },

    JAIL(8, "") {  // Where people will spawn when they die in enemy (neutral) towns
    },*/
}
