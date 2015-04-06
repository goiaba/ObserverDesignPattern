package edu.luc.cs.spring2015.cs473;

public interface Subject<StateType> {

	void attach(Observer observer);

	void detach(Observer observer);

	void notifyObserver();
	
	StateType getState();
	
	void setState(StateType state);
	
}
