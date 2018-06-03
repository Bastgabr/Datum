package Labor_5;
import java.text.DecimalFormat;

public class Datum {
	private int iJahr;
	private int iMonat;
	private int iTag;
	
	private static int iAnzahlDatumObjekte = 0;
	
//================Setter and Getter====================================
	public int getiJahr() {
		return iJahr;
	}
	public void setiJahr(int iJahr) {
		this.iJahr = iJahr;
	}
	public int getiMonat() {
		return iMonat;
	}
	public void setiMonat(int iMonat) {
		this.iMonat = iMonat;
	}
	public int getiTag() {
		return iTag;
	}
	public void setiTag(int iTag) {
		this.iTag = iTag;
	}
	
	
/**--------------------------------------------------------------------------
 * @fn 			public Datum()
 * @brief 		Construct fonction initialisation with Jahr = 1970, Monat = 1,
 * 				Tag = 1
 * @return		nothing 
 * @param		nothing
 * @author		Gabriel Bastien
 * @date		09.04.2018
 ----------------------------------------------------------------------------*/
	public Datum() {
		this.iJahr = 1970;
		this.iMonat = 1;
		this.iTag = 1;
		iAnzahlDatumObjekte++;
	}
/**--------------------------------------------------------------------------
 * @fn 			public Datum(int neuTag, int neuMonat, int neuJahr)
 * @brief 		Construct fonction 
 * @return		nothing 
 * @param		int neuTag, int neuMonat, int neuJahr
 * @author		Gabriel Bastien
 * @date		09.04.2018
----------------------------------------------------------------------------*/
	public Datum(int neuTag, int neuMonat, int neuJahr){
		boolean bDatumIstOK;
		bDatumIstOK = bpruefeDatum(neuJahr, neuMonat, neuTag);
		if(bDatumIstOK) {
			this.iJahr = neuJahr;
			this.iMonat = neuMonat;
			this.iTag = neuTag;
			iAnzahlDatumObjekte++;
		}
		else {
			this.iJahr = 1970;
			this.iMonat = 1;
			this.iTag = 1;
		}
	}
/**--------------------------------------------------------------------------
 * @fn 			public Datum(Datum anderesDatum)
 * @brief 		Construct fonction 
 * @return		nothing 
 * @param		Datum anderesDatum
 * @author		Gabriel Bastien
 * @date		09.04.2018
----------------------------------------------------------------------------*/
	public Datum(Datum anderesDatum) {
		this.iJahr = anderesDatum.iJahr;
		this.iMonat = anderesDatum.iMonat;
		this.iTag = anderesDatum.iTag;
		iAnzahlDatumObjekte++;
	}

/**--------------------------------------------------------------------------
 * @fn 			public boolean bpruefeDatum(int iJahr,int iMonat,int iTag)
 * @brief 		Test if Date is valid
 * @return		true if Valid / false if invalid
 * @param		int iJahr, int iMonat, int iTag
 * @author		Gabriel Bastien
 * @date		09.04.2018
----------------------------------------------------------------------------*/	
	public boolean bpruefeDatum(int iJahr,int iMonat,int iTag) {
		boolean bMonatgultig;
		boolean bTaggultig;
		if((iJahr >= 1970)&&(iJahr<=9999))  							// Test is the Year is greater equal 1970
		{
			bMonatgultig = bPruefeMonat(iMonat);  	//Call function pruefeMonat (1 = Month is Ok)
			if (bMonatgultig)						//Test if Month is OK
			{
				if(iTag > 0 && iTag <= 31)			//If yes test if the Day is between 1 and 31
				{
					bTaggultig = bPruefeTag(iJahr, iMonat, iTag);	//If Yes test if the day correspond to month and Schaltjahr
					if (bTaggultig) return true;					//If the whole parameters (day/Month/Year) are ok return true
					else return false;
				}
				else return false;
			}
			else return false;
			
		}
		else return false;
	}
/**--------------------------------------------------------------------------
 * @fn 			public boolean bsetDatum(int neuTag, int neuMonat, int neuJahr)
 * @brief 		Redefine Attributes of an  existing Object 
 * @return		true if Date is valid / false if Date invalid 
 * @param		int neuTag, int neuMonat, int neuJahr
 * @author		Gabriel Bastien
 * @date		09.04.2018
----------------------------------------------------------------------------*/
	public boolean bsetDatum(int neuTag, int neuMonat, int neuJahr) {
		if( bpruefeDatum(neuJahr, neuMonat, neuTag)) {	//Test if Date is Valid
			this.iJahr = neuJahr;						//Set Attributes
			this.iMonat = neuMonat;
			this.iTag = neuTag;	
			return true;								
		}
		else return false;								//Return false if Date is invalid
	}

/**--------------------------------------------------------------------------
 * @fn 			public String toString()
 * @brief 		Convert Obj Attributes to String
 * @return		String "dd.mm.yyy"
 * @param		nothing
 * @author		Gabriel Bastien
 * @date		09.04.2018
----------------------------------------------------------------------------*/
	public String DatumtoString(){ 
		DecimalFormat df = new DecimalFormat("00");
		return ""+df.format(this.iTag) +"."+df.format(this.iMonat) +"."+this.iJahr;
	}
/**--------------------------------------------------------------------------
 * @fn 			public boolean istVorher(Datum anderesDatum)
 * @brief 		Test if the Date object is before the given Date object
 * @return		true if the Date object is before the given Date object / False if not
 * @param		Datum anderesDatum
 * @author		Gabriel Bastien
 * @date		09.04.2018
----------------------------------------------------------------------------*/
	public boolean istVorher(Datum anderesDatum) {
		if(anderesDatum.iJahr == this.iJahr)
		{
			if(anderesDatum.iMonat == this.iMonat)
			{
				if(anderesDatum.iTag == this.iTag)
				{
					return false;
				}
				else if (anderesDatum.iTag > this.iTag)
				{
					return true;
				}
				else return false;
			}
			else if (anderesDatum.iMonat > this.iMonat)
			{
				return true;
			}
			else return false;
		}
		else if(anderesDatum.iJahr > this.iJahr)
		{
			return true;
		}
		else return false;
	}
/**--------------------------------------------------------------------------
 * @fn 			public Datum addiereTag()
 * @brief 		Add a day to the given Date 
 * @return		Datum
 * @param		nothing
 * @author		Gabriel Bastien
 * @date		09.04.2018
----------------------------------------------------------------------------*/
	public Datum addiereTag() {
		Datum Tagadded = new Datum(this.iTag,this.iMonat,this.iJahr);
		Tagadded.iTag++;
		if(bpruefeDatum(Tagadded.iJahr,Tagadded.iMonat, Tagadded.iTag)) return Tagadded;
		else {
			Tagadded.iTag = 1;
			Tagadded.iMonat++;
			if(bpruefeDatum(Tagadded.iJahr,Tagadded.iMonat, Tagadded.iTag))
				return Tagadded;
			else {
				Tagadded.iMonat = 1;
				Tagadded.iJahr++;
				return Tagadded;
			}
		}	
	}
/**--------------------------------------------------------------------------
 * @fn 			public int getAnzahlDatumObjekte()
 * @brief 		Give back the number of created objects
 * @return		int
 * @param		nothing
 * @author		Gabriel Bastien
 * @date		09.04.2018
----------------------------------------------------------------------------*/
	public int getAnzahlDatumObjekte() {
		return iAnzahlDatumObjekte-1; //Minus 1 because AddiereTag is creating a new object to be able to return an Object 
	}
	
/**--------------------------------------------------------------------------
 * @fn 			private static boolean Schaltjahr(int iJahr)
 * @brief 		Test if year is a Lapyear 
 * @return		true if year is a Lapyear / false if not 
 * @param		int iJahr
 * @author		Gabriel Bastien
 * @date		09.04.2018
----------------------------------------------------------------------------*/
	private static boolean Schaltjahr(int iJahr) {
		if (iJahr % 4 == 0) {
			if (iJahr % 100 == 0 && iJahr % 400 == 0) {
				return true;
			} else {
				if (iJahr % 100 == 0 && iJahr % 400 != 0) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
/**--------------------------------------------------------------------------
 * @fn 			private boolean bPruefeMonat(int iMonat)
 * @brief 		Test if Month number is Valid
 * @return		true if Valid / false if invalid
 * @param		int iMonat
 * @author		Gabriel Bastien
 * @date		09.04.2018
----------------------------------------------------------------------------*/	
	private boolean bPruefeMonat(int iMonat)
	{
		if(iMonat >= 1 && iMonat <= 12)
			return true;
		else return false;
	}
/**--------------------------------------------------------------------------
 * @fn 			private boolean bPruefeTag(int iJahr, int iMonat, int iTag)
 * @brief 		Test if Day number is valid (depending on the month and year)
 * @return		true if Valid / false if invalid
 * @param		int iJahr, int iMonat, int iTag
 * @author		Gabriel Bastien
 * @date		09.04.2018
----------------------------------------------------------------------------*/
	private boolean bPruefeTag(int iJahr, int iMonat, int iTag)
	{
		boolean bIstSchlatjahr;
		switch(iMonat)
		{		
			case 2:	//February
				bIstSchlatjahr = Schaltjahr(iJahr); //Test if the year is a schlatjahr
				if(bIstSchlatjahr){					//If year is a schaltjahr february has 29 days
					if (iTag > 29)
						return false;				//Then if the day is greater than 29 return false 
					else return true;
				}
				else {								//If year is not a schaltjahr february has 28 day
					if (iTag > 28)					
						return false;		
					else return true;	
				}
			case 4: //April
				if(iTag > 30) return false; //if the day is greater than 30 return false
				else return true;			//otherwise return true
			case 6 : //June
				if(iTag > 30) return false;
				else return true;
			case 9: //September
				if(iTag > 30) return false;
				else return true;
			case 11: //November
				if(iTag > 30) return false; 
				else return true;	
			default : return true;	//For all other month (31 -Days month) return true	
		}
	}
	
}
