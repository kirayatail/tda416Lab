package edu.chl.sebljunwmax.exercise;

import java.util.*;

public class PriorityQueue<E extends Comparable<E>> {
	private List<E> pqList;
	
	public PriorityQueue(){
		pqList = new ArrayList<E>();
		
	}
	
	public static void main(String[] args){
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		pq.add(5);
		pq.add(2);
		pq.add(12);
		pq.add(22);
		pq.add(45);
		pq.add(4);
		pq.add(15);
		pq.add(74);
		pq.add(31);
		pq.add(23);
		System.out.println(pq);
		for(int i=0; i<10;i++){
			System.out.print(pq.pop()+" ");
		}
	}
	
	public void add(E elm){
		pqList.add(elm);
		int j = pqList.size()-1;
		while(j>0 && pqList.get(j).compareTo(pqList.get(j/2)) < 0){
			swap(j, j/2);
			j = j/2;
		}
	}
	
	public E pop(){
		E result = pqList.get(0);
		pqList.set(0, pqList.get(pqList.size()-1));
		pqList.remove(pqList.size()-1);
		int i = 1;
		boolean done = false;
		while(i<pqList.size()/2 && !done){
			int j;
			if(pqList.get(i*2-1).compareTo(pqList.get(i*2))<=0){
				j = i*2-1;
			} else {
				j = i*2;
			}
			
			if(pqList.get(i-1).compareTo(pqList.get(j))<0){
				swap(i-1, j);
				i=j+1;
			} else {
				done = true;
			}
		}
		return result;
	}
	
	private void swap(int a, int b){
		E temp = pqList.get(a);
		pqList.set(a, pqList.get(b));
		pqList.set(b, temp);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i< pqList.size()-1; i++){
			sb.append(pqList.get(i).toString());
			sb.append(" ");
		}
		sb.append(pqList.get(pqList.size()-1).toString());
		return sb.toString();
	}
}
