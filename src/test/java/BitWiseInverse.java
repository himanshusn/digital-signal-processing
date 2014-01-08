import static org.junit.Assert.*;

import org.junit.Test;


public class BitWiseInverse {

	@Test
	public void test() {
		for (int N = 8; N > 1; N /= 2)// etapy
		{ 
			int motylkiWbloku = N / 2;
			int liczbaBlokow = 8 /N;
			for (int i = 0; i < liczbaBlokow; i++) 
			{
				for (int j = 0; j < motylkiWbloku; j++) 
				{
					int indexA = i * N + j;
					int indexB = indexA + motylkiWbloku;
					System.out.println(indexA + " " + indexB);
				}
				System.out.println();
			}
		}
	}
	
	int bitWiseInverse(int value, int bitPosition){
//		int bitPosition = greatestBit(value);
		int inverseValue = 0;
		
		for (int i = 0; i < bitPosition; i++) {
			boolean bit = (value & (1 << i)) > 0;
			if(bit){
				inverseValue |= (1 << bitPosition - i - 1);
			}else{
				inverseValue &= ~(1 << bitPosition - i - 1);
			}
		}
		return inverseValue;
	}
	
	int greatestBit(int value){
		int bitPosition = 0;
		
		for (int i = 31; i > 0; i--) {
			int power = (1 << (i));
			int debug = value & power;
			if(debug > 0){
				break;
			}
			bitPosition++;
		}
		return 32 - bitPosition;
	}

}
