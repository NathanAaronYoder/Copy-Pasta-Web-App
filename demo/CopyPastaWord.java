package com.example.demo;

class CopyPastaWord{
	private String word;
	private String username;

	public CopyPastaWord(){}

	public CopyPastaWord(String word, String username)
	{
		this.word = word;
		this.username = username;
	}

	public String getWord()
	{
		return word;
	}

	public String getUsername()
	{
		return username;
	}

	public void setWord(String word)
	{
		this.word = word;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	@Override
  	public String toString() {
    	return "Word: " + word + "\nUsername: " + username;
  	}
}