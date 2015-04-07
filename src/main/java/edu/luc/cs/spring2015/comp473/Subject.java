package edu.luc.cs.spring2015.comp473;

import java.util.List;

public interface Subject<StateType> {

	void attach(Observer observer);

	void detach(Observer observer);

	void notifyObserver();
	
	StateType getState();
	
	void setState(StateType state);
	
	List<Observer> getObservers();
	
}
