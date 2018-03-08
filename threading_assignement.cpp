#include <iostream> 
#include <thread> 
#include <vector> 

using namespace std; 

int maximum, minimum; 
double average;

void find_average(vector<int> list) {
	average = 0;  
	for(int i=0; i<list.size(); i++) { 
		average += list[i]; 
	}
	average /= list.size(); 
}

void find_maximum(vector<int> list) {
	maximum = list[0];

	for(int i=1; i<list.size(); i++) { 
		if (maximum < list[i])
		 	maximum = list[i]; 
	}
}

void find_minimum(vector<int> list) {
	minimum = list[0];  
	
	for(int i=1; i<list.size(); i++) { 
		if (minimum > list[i])
		 	minimum = list[i]; 
	}
}

int main(int argc, char* argv[]) {
	int input;  
	std::vector<int> list;
	cout << "\n\n********************************************\nEnter your list, to exit enter (0): "; 
	cin >> input; 
	while(input) {
		list.push_back(input); 
		cout << "Enter another element or (0) to exit: "; 
		cin >> input; 
	}

	/*
	/* Threading
	/* thread.join() tells the parent/main thread to wait until 
	/* the child thread finishes its work
	/* we do that to force the program to exit after finding the wanted values
	*/ 	
	thread t_average(find_average, list); 
	t_average.join();
	thread t_minimum(find_minimum, list);
	t_minimum.join(); 
	thread t_maximum(find_maximum, list); 
	t_maximum.join(); 

	cout << "Average Value: " << average << endl;
	cout << "Maximum Value: " << maximum << endl;
	cout << "Minimum Value: " << minimum << endl	;
	cout << "********************************************\n\n"; 
	return 0; 
}