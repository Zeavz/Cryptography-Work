#include <cstdio>
#include <iostream>
using namespace std;

int gcd(int a, int b){
	return b == 0 ? a : gcd(b,a%b);
}
int modularinverse(int inverse, int mod){
	for(int i = 1; i<mod;i++){
		if((inverse*i) % mod == 1){
			return i;
		}
	}
}
int encrypt(int publickey[]){
	int message[] = {0,1,1,0,1,1,0,1};
	int encoded=0;
	for(int i=0;i<8;i++){
		encoded += message[i]*publickey[i];
	}
	cout << "Melanie: I encrypted the letter 'a' (01101101) and will now send it back to Jenis as " << encoded << "\n";
	return encoded;
	
}
void decrypt(int privateknapsack[], int message, int r, int q){
	int rinverse;
	rinverse = modularinverse(r,q);
	int temp;
	temp = (message * rinverse) % q;
	int final[8];
	for(int i=7;i>-1;i--){
		if(temp == 0){final[i] = 0;}
		else if(temp - privateknapsack[i] >= 0){
			final[i] = 1;
			temp -= privateknapsack[i];
		}
		else{final[i] = 0;}
	}
	cout << "\n" << "Decrypted: ";
	for(int j=0;j<8;j++){
		cout << final[j];
	}
	cout << "\n\n";
}

int main(){
	int privateknapsack[8] = {1,2,6,11,23,46,92,183};
	int sum=0;
	cout << "\n";
	cout << "Jenis: Hello, This is my private key:\n";
	for(int i=0;i<8;i++){
		cout << privateknapsack[i] << " ";
		sum += privateknapsack[i];
	}
	cout << "\n";
	cout << "\n" << "Jenis: and this is my public key:\n";
	int publicknapsack[8];
	int q,r;
	q = sum + 15;
	int count = 0;
	for(int j=2;j<q;j++){
		if(gcd(j,q) == 1){
			
			if(count == 3){
				r = j;
				break;
			}
			count++;
		}
	}
	for(int k=0;k<8;k++){
		publicknapsack[k] = (int)(privateknapsack[k] * r)%q;
		cout << publicknapsack[k] << " ";
	}
	cout << "\n\n";
	cout << "Jenis: Now i am going to send Melanie my public key so that she can send me an encrypted message " << "\n\n";	
	int encodedmessage;
	encodedmessage = encrypt(publicknapsack);
	cout << "Jenis: I will now use my private key to decrypt the message" << "\n";
	decrypt(privateknapsack, encodedmessage, r, q);
}