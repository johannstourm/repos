package com.stourm.mtgappariement.entities;



public class ResultatId {


		private Username user;
		private Tournoi tournoi;
		private Integer round;
		
		
		public ResultatId() {
			super();
			// TODO Auto-generated constructor stub
		}


		public ResultatId( Username user, Tournoi tournoi) {
			super();
			this.user = user;
			this.tournoi = tournoi;
		}
		
		public Username getUser() {
			return user;
		}



		public void setUser(Username user) {
			this.user = user;
		}



		public Tournoi getTournoi() {
			return tournoi;
		}



		public void setTournoi(Tournoi tournoi) {
			this.tournoi = tournoi;
		}


		public Integer getRound() {
			return round;
		}


		public void setRang(Integer round) {
			this.round = round;
		}

		
}
