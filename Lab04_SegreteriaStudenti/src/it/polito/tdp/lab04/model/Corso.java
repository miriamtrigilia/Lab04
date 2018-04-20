package it.polito.tdp.lab04.model;


public class Corso {
	
	private String codiceIns;
	private int crediti;
	private String nome;
	private int pd;
	
	public Corso(String codiceIns, int crediti, String nome, int pd) {
		super();
		this.codiceIns = codiceIns;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}

	public String getCodiceIns() {
		return codiceIns;
	}

	public void setCodiceIns(String codiceIns) {
		this.codiceIns = codiceIns;
	}

	public int getCrediti() {
		return crediti;
	}

	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPd() {
		return pd;
	}

	public void setPd(int pd) {
		this.pd = pd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceIns == null) ? 0 : codiceIns.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codiceIns == null) {
			if (other.codiceIns != null)
				return false;
		} else if (!codiceIns.equals(other.codiceIns))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return codiceIns + "," + crediti + "," + nome + "," + pd;
	}
	
	
	

}
