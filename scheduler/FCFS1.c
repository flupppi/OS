/*
Program : First Come First Serverd Scheduling Algorithm
Language : C
*********/
#include<stdio.h>
struct PCB{
	int pid, arrival, burst, turnaround;
};

struct object {
	char Name [20];
	int pID, duration;
	struct object* next;
};

struct queue{
	struct object *front, *last;
};

struct object* newProcess (char *Name, int pID, int duration)
{
	struct object* temp = (struct object*)malloc(sizeof(struct object));
	temp->Name = Name;
	temp -> pID = pID;
	temp -> duration = duration;
	temp -> next = NULL;

	return temp;
}

struct queue* createQueue (){
	struct queue* q = (struct queue*) malloc (sizeof(struct queue));
	q -> front = q -> last = NULL;
};

void addProcess (struct queue * q, char *Name, int pID, int duration){
	struct object* temp= newProcess(Name, pID, duration);

	if ( q->last ==NULL)
	{
		q->front = q->last = temp;
		return;
	}

	q -> last-> next = temp;
	q -> last = temp;

}
void deleteProcess (struct queue* q){
	if (q -> front == NULL)
		return;

	struct object* temp = q->front;

	q->front = q->front->next;

	if(q -> front == NULL)
		q -> last = NULL;

	free(temp);
	};

void pline(int x);
int main()
{
	int i, num, j;
	float avg = 0.0, sum = 0.0;
	struct PCB p[10], temp;// create linked list
	printf("Enter the total number of Processes: ");
	scanf("%d", &num);
	for(i = 0; i < num; i++)
	{
		printf("Enter Name time and Duration for Process %d : \n", i + 1);
		scanf("%d %d", &p[i].arrival, &p[i].burst);
		p[i].pid = i + 1;// put value into linked list
	}

	//call sorting algorithm sortlinkedlist()
	for ( i = 0; i < num; i ++)
	{
		for (j=0; j<num-i-1; j++)
		{
			if(p[j].arrival > p[j+1].arrival)
			{
				temp = p[j];
				p[i] = p[j+1];
				p[j+1] = temp;
		}
	}
	for (i = 0; i < num; i++)
	{
		sum = sum + p[i].burst;
		p[i].turnaround = sum;
	}
	sum = 0;
	pline(44);
	printf("PID\tArrival\tBurst\tTurnaround\n");
	pline(44);
	for(i = 0; i < num; i++)
	{
		printf("%d\t%d\t%d\t%d\n", p[i].pid, p[i].arrival, p[i].burst, p[i].turnaround);
		sum += p[i].turnaround;
	}
	pline(44);
	avg = sum/ (float)num;
	printf("\nTotal Turnaround Time : %f.", sum);
	printf("\nAverage Turnaround Time : %.3f.", avg);
	}
}
void pline(int x)
{
	int i; 
	for (i = 0; i < x; i++)
	{
		printf("-");
	}
	printf("\n");
}

