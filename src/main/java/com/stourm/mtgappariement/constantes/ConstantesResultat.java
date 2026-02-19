package com.stourm.mtgappariement.constantes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConstantesResultat {

	public static final int FIRST_ROUND = 1;
	
	public static final int SCORE_INITIAL = 0; 
	
	public static final int SCORE_ADD_WON = 3;
	
	public static final int SCORE_ADD_LOST = 0;
	
	public static final int SCORE_ADD_DRAW = 1;

	public static List<Integer> LST_NB_ROUND_WON = Collections.unmodifiableList(Arrays.asList(0,1,2));
}
