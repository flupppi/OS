/*
 * Bankers's Algorithm
 * Author: Felix Kalchschmid
 * 
 * Task: Write a program to implement the Banker’s Algorithm. 
 * The pseudocode for the algorithm is in the book and in the slides in Chapter 7.
 * Your code should check if the system is in a safe state and determine<<
 * if a process should be assigned the requested resources or not.
 */

#include <iostream>
#include <map>
#include <cstdlib>
#include <time.h>
#include <queue>

using namespace std;

int resourceCount;
int processCount;

class Process{
	// Variables
 int pId;
 bool finished;
 map<char, int> resourcesRequirement>;
 map<char, int> maxRequirement;
 map<char, int> allocation;
 public:
		// Getter
		int getPId()
		{
			return pId;
		}
		int getFinished()
		{
			return finished;
		}
		int getResourceRequirement(char res)
		{
			return resourcesRequirement[res];
		}
		int getMaxRequirement(char res)
		{
			return maxRequirement[res];
		}
		int getAllocation(char res)
		{
			return allocation[res];
		}
		// Setter
		int setpId(int pId)
		{
			this -> pId = pId;
		}
		bool setfinished(bool finished)
		{
			this->finished = finished;
		}
		void setResourceRequirement(char res, int req)
		{
			resourcesRequirement.insert(make_pair(res, req));
		}
		void setMaxRequirement(char res, int req)
		{
			maxRequirement.insert(make_pair(res, req));
		}
		void setAllocation(char res, int req)
		{
			allocation.insert(make_pair(res, req));
		}
 };
class Resource{
	 char resourceId;
	 int maxAvailable;
	 int currentAvailable;
	 public:
			char getResourceId()
			{
				return resourceId;
			}	
			int getMaxAvailable()
			{
				return maxAvailable;
			}
			int getCurrentAvailable()
			{
				return currentAvailable;
			}
			void setResourceId(char resourceId)
			{
				this->resouceId = resourceId;
			}
			void setMaxAvailable(int maxAvailable)
			{
				this->maxAvailable = maxAvailable;
			}
			void setCurrentAvailable(int currentAvailable)
			{
				this->currentAvailable = currentAvailable;
			}
			
};



void display(Process P[], Resource R[])
{
	
} 

void generateRandomData(Process P[], Resource R[])
{
	// In order to generate random number between range min and max
	
}

int resourceLeft(Resource R, Process P[])
{
	
}

void bankersAlgorithm(Process P[], Resource R[])
{

}

void enterData(Process P[], Resource R[])
{
	int x;
	char a;
	cout<<"\n Resource data: \n\n";
	for(int i=0; i<resourceCount; i++)
	{
		cout<<"\n\t Resource " <<i+1;
		cout<<"\nEnter the resource id: ";
		cin>>a;
		R[i].setResourceId(a);
		cout<<"\nEnter total resource available: ";
		cin>>x;
		R[i}.setMaxAvailable(x);
	}
	
	cout<<"\n Process data: \n\n";
	for(int i=0; i<processCount; i++)
	{
		cout<<"\n\t Process " <<i+1;
		cout<<"\nEnter the process id: ";
		cin>>a;
		P[i].PId(x);
		for(int j=0; j<resourceCount; j++)
		{
			cout<<"\n\n Resource "<<R[j].getResourceId();
			cout<<"\n Enter maximum requirement: ";
			cin>>x;
			P[i].setMaxRequirement(R[j].getResourceId(),x);
			cout<<"\n Enter resource allocated: ";
			cin>>x;
			P[i].setAlloation(R[j].getResourceId(),x);
		}
	}
	
}

void main()
{
	int choice;
	while(true){
		cout<<"\n\n\t\t ``Banker's Algorithm``\n \t\t ------------------\n";
		cout<<"\n\n Manually enter the data or go for computer generated data?";
		cout<<"\n 1. Feed the data\t 2. Auto generated\t 0. Exit";
		cout<<"\n\n Enter the choice [0-2}:";
		cin>>choice;
		switch(choice)
		{
			case 1: {
				cout<<"\n Number of Processes: ";
				cin>>processCount;
				cout<<"\n Number of Resources: ";
				cin>>resourceCount;
				Process P[processCount];
				Resource R[resourceCount];
				enterData(P,R);
				//bankersAlgorithm(P,R);
				display(P,R);
				break;
			}
			case 2: {
				cout<<"\n Number of Processes: ";
				cin>>processCount;
				cout<<"\n Number of Resources: ";
				cin>>resourceCount;
				Process P[processCount];
				Resource R[resourceCount];
				generateRandomData(P,R);
				//bankersAlgorithm(P,R);
				display(P,R);
				break;
			}
			case 0: {
				cout<<"\n Thanks!";
				exit(1);
			}
