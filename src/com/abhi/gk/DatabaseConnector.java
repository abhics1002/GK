package com.abhi.gk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DatabaseConnector {
	
	public static final String DATABASE_NAME = "QUIZ";
	public SQLiteDatabase database;
	public DatabaseOpenHelper databaseOpenHelper;	//database open helper 
	
	//constructor 
	
	public DatabaseConnector(Context context)
	{
		databaseOpenHelper = new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
	}
	
	// open a database connection 
	public void open() throws SQLException
	{
		Log.v("INFO", "DatabaseConnector-- open ---- START");
		database = databaseOpenHelper.getWritableDatabase();	
		Log.v("INFO", "DatabaseConnector-- open ---- END");
	}
	
	//close a database connection 
	public void close()
	{	
		Log.v("INFO", "DatabaseConnector-- close ---- START");
		if(database !=  null)
		{
			database.close();
		}
		
		Log.v("INFO", "DatabaseConnector-- close ---- END");
	}
	
	public Cursor getQuestions()
	{
		//open();
		Log.v("INFO", "DatabaseConnector-- getQuestions ---- START");
		return database.query("QUIZ",new String[] {"question", "a","b","c","d","answer"}, null, null, null, null, null);
		
		
	}
	
	public Cursor getQuestionsUsingPreferences(String question_id_prefernces , String category)
	{	
		
		Log.v("DATABASECONNECTOR", "DatabaseConnector-- getQuestionsUsingPreferences ---- START");
		Log.v("DATABASECONNECTOR", "DatabaseConnector-- category"+category);
		String temp;
		if(category.equals("all") || category.equals("ALL"))
		{
			temp= "SELECT DISTINCT * FROM QUIZ WHERE question_id NOT IN ("+question_id_prefernces+");";
			Log.v("getQuestionsUsingPreferences", temp);
		}
		else
		{
			temp= "SELECT DISTINCT * FROM QUIZ WHERE question_id NOT IN ("+question_id_prefernces+") and category ='"+category+"';";
			Log.v("getQuestionsUsingPreferences ---- with category enables", temp);
		}
		
		return database.rawQuery(temp, null);
				
	}
	public Cursor getOneQuestion()
	{	System.out.print("getOneQuestion----------START");
		open();
		// give question id randomly.
		int id = 2;
		return database.query(DATABASE_NAME, null,"question_id = "+ id, null, null, null, null);
	}
	
	public void insertQuestion(int question_id, String question, String a, String b, String c, String d, String answer, String category)
	{
		Log.v("INFO", "insertQuestion ---- START");
		ContentValues ques = new ContentValues();
		ques.put("question_id", question_id);
		ques.put("question", question);
		ques.put("a", a);
		ques.put("b", b);
		ques.put("c", c);
		ques.put("d", d);
		ques.put("answer", answer);
		ques.put("category", category);
		open();
		try{
			Log.v("INFO", "DB insert-- before inserting record");
			database.insert("QUIZ", null, ques);
			Log.v("INFO", "DB insert-- After inserting record");
		}
		catch(SQLException ex)
		{
			Log.v("INFO", "EXCEPTION --insert error in inserting record ");
		}
		
		close();
		Log.v("INFO", "insertQuestion ---- END");
	}
	
	public void deleteQuestion(int id)
	{
		open();
		database.delete(DATABASE_NAME, "id = "+ id , null);
		close();
	}
	
	public void getQuestionAnswer(int id)
	{
		open();
		database.query(DATABASE_NAME, null, "id ="+id, null, null, null, null);
		close();
	}
	
	public void populateDatabase()
	{
		Log.v("INFO", "DatabaseOpenhelper::populateDatabase ---- START");
		try{
	
		insertQuestion(1,"who is the president of india ?","DR Abdul Kalam", "Pranav Mukharji","Manmohansingh","Dr Sankar dayal sharma","b","P");
		insertQuestion(2,"Kiran Bedi received Magsaysay Award for government service in", "1992","1993","1994","1995","c","P");
		insertQuestion(3,"Logarithm tables were invented by","John Napier", "John Doe","John Harrison","John Douglas","a","S");
		insertQuestion(4,"With which sport is the Jules Rimet trophy associated?","Basketball", "Football","Hockey","Golf","b","S");
		insertQuestion(5,"Joule is the unit of","temperature", "pressure","energy","heat","c","S");
		insertQuestion(6,"Kemal Ataturk was","the first President of Independent Kenya", "the founder of modern Turkey","revolutionary leader of Soviet Union","None of the above","b","S");
		insertQuestion(7,"Milkha Singh Stood ____ in 1960 Olympics, in Athletics.","fourth in 400m final", "second in 400m final","eighth in 50km walk","seventh in 800m final","a","S");
		insertQuestion(8,"Ms. Medha Patkar is associated with the","Tehri project", "Enron project","Sardar Sarovar project","Dabhol project","c","P");
		insertQuestion(9,"Kathakali, Mohiniatam and Ottamthullal are the famous dances of","Kerala", "Karnataka","Orissa","Tamil Nadu","a","G");
		insertQuestion(10,"Jaspal Rana is associated with which of the following games?","Swimming", "Archery","Shooting","Weightlifting","c","S");
		insertQuestion(11,"Lala Lajpat Rai is also known as","Sher-e-Punjab", "Punjab Kesari","both (a) and (b)","None of the above","c","H");
		insertQuestion(12,"Modern football is said to have evolved from","England", "India","France","Spain","a","S");	
		insertQuestion(13,"The members of the Rajya Sabha are elected by","the people", "Lok Sabha","elected members of the legislative assembly","elected members of the legislative council","c","P");
		insertQuestion(14,"The power to decide an election petition is vested in the","Parliament", "Supreme Court","High courts","Election Commission","c","P");
		insertQuestion(15,"The present Lok Sabha is the","13th Lok Sabha", "14th Lok Sabha","15th Lok Sabha","16th Lok Sabha","c","P");
		insertQuestion(16,"The name of a candidate for the office of president of India may be proposed by","any five citizens of India", "any five members of the Parliament","any one member of the Electoral College","any ten members of the Electoral College","d","P");
		insertQuestion(17,"The famous Meenakshi temple is located in","Bihar", "Madurai","Madras","Trichy","b","G");
		insertQuestion(18,"Tin Bhiga lease by India to Bangladesh, was a part of","West Bengal", "Assam","Meghalaya","Tripura","a","G");
		insertQuestion(19,"National Science Centre is located at","Bangalore", "Bombay","Kolkata","Delhi","d","G");
		insertQuestion(20,"The world famous Ajanta caves are situated in","Orissa", "Andhra Pradesh","Kerala","Maharashtra","a","G");
		insertQuestion(21,"Former Australian captain Mark Taylor has had several nicknames over his playing career. Which of the following was NOT one of them?","Tubby", "Stodge","Helium Bat","Stumpy","d","G");
		insertQuestion(22,"Which was the 1st non Test playing country to beat India in an international match?","Canada", "Sri Lanka","Zimbabwe","East Africa","b","S");
		insertQuestion(23,"Who is the first Indian woman to win an Asian Games gold in 400m run?","M.L.Valsamma", "P.T.Usha","Kamaljit Sandhu","K.Malleshwari","c","S");
		insertQuestion(24,"Two of the great Mughals wrote their own memories. There were","Babar and Humayun", "Humayun and Jahangir","Babar and Jahangir","Jahangir and Shahjahan","c","H");
		insertQuestion(25,"To which king belongs the Lion capitol at Sarnath?","Chandragupta", "Ashoka","Kanishka","Harsha","b","H");
		insertQuestion(26,"The language of discourses of Gautama Buddha was","Bhojpuri", "Magadhi","Pali","Sanskrit","c","H");
		insertQuestion(27,"Under the Mountbatten Plan of 1947 the people of ___ were given the right to decide through a plebiscite whether they wished to join Pakistan or India.","Assam", "Punjab","Bengal","N.W.F.P and the Sylhet district of Assam","d","H");
		insertQuestion(28,"Which player has scored the most runs in a single Test innings?","Graham Gooch", "Matthew Hayden","Brian Lara","Agarkar","c","S");
		insertQuestion(29,"Who was the 1st ODI captain for India?","Ajit Wadekar", "Bishen Singh Bedi","Nawab Pataudi","Vinoo Mankad","a","S");
		insertQuestion(30,"The Asian Games were held in Delhi for the first time in...?","1951", "1962","1971","1982","a","S");
		insertQuestion(31,"The lead character in the film The Bandit Queen has been played by","Rupa Ganguly", "Seema Biswas","Pratiba Sinha","Shabama Azmi","b","H");
		insertQuestion(32,"Who was the first captain of Indian Test team?","Vijay Hazare", "C K Nayudu","Lala Amarnath","Vijay Merchant","b","S");
		insertQuestion(33,"Who wrote the famous book - We the people?","T.N.Kaul", "J.R.D. Tata","Khushwant Singh","Nani Palkhivala","d","B");
		insertQuestion(34,"Which of the following is NOT written by Munshi Premchand?","Gaban", "Godan","Guide","Manasorovar","c","A");
		insertQuestion(35,"Who has been awarded the first lifetime Achievement Award for his/her contribution in the field of Cinema?","Ashok Kumar", "Hou Hsio-hsein","Akiro Burosova","Bernardo Burtolucci","a","A");
		insertQuestion(36,"Gandhi Peace Prize for the year 2000 was awarded to the former President of South Africa along with","Sathish Dawan", "C. Subramanian","Grameen Bank of Bangladesh","World Healt Organisation","c","A");
		insertQuestion(37,"B. C. Roy Award is given in the field of","Music", "Journalism","Medicine","Environment","c","A");
		insertQuestion(38,"If an economy is equilibrium at the point where plans to save and to invest are equal, then government expenditure must be","zero", "equal to government income","larger than government income","negative","b","E");
		insertQuestion(39,"Which of the following is not an undertaking under the administrative control of Ministry of Railways?","Container Corporation of India Limited", "Konkan Railway Corporation Limited","Indian Railways Construction Company Limited","Diesel Locomotive Works, Varanasi","c","E");
		insertQuestion(40,"How many gold medals have been won by India in the Olympics so far?","4", "8","9","10","c","S");
		insertQuestion(41,"When was the first cricket Test match played?","1873", "1877","1870","1788","b","S");
		insertQuestion(42,"The first hand glider was designed by...?","Leonardo DaVinci", "The Wright brothers","Francis Rogallo","Galileo","a","G");
		insertQuestion(43,"In which Indian state did the game of Polo originate?","Meghalaya", "Rajasthan","Manipur","West Bengal","c","S");
		insertQuestion(44,"The central banking functions in India are performed by the (I): Central Bank of India   (II): Reserve Bank of India  (III): State Bank of India  (IV): Punjab National Bank","I II", "II","I","I III","b","E");
		insertQuestion(45,"Gilt-edged market means","England", "India","France","Spain","a","S");
		insertQuestion(46,"National expenditure includes","consumption expenditure", "investment expenditure","government expenditure","All of the above","d","E");
		insertQuestion(47,"The apex body for formulating plans and coordinating research work in agriculture and allied fields is","State Trading Corporation", "Regional Rural Banks","National Bank for Agriculture and Rural Development (NABARD)","Indian Council of Agricultural Research","d","E");
		insertQuestion(48,"Which of the following is not viewed as a national debt?","Provident Fund", "Life Insurance Policies","National Saving Certificate","Long-term Government Bonds","c","E");
		insertQuestion(49,"The condition of indirect taxes in the revenue is approximately","70 percent", "75 percent","80 percent","86 percent","d","E");
		insertQuestion(50,"Revenue of the state governments are raised from the following sources, except","entertainment tax", "expenditure tax","agricultural income tax","land revenue","c","E");
		insertQuestion(51," Which of the following is not the source of the revenue of central Government? "," Income Tax "," Corporate Tax "," Agricultural Income Tax "," Excise Duty ","c","eco");
		insertQuestion(52,"QUESTION","A","B","C","D","ANSWER","CATEGORY");
		insertQuestion(53," Which of the following is not the source of the revenue of central Government? "," Income Tax "," Corporate Tax "," Agricultural Income Tax "," Excise Duty ","c","eco");
		insertQuestion(54," Operation Flood is associated with– "," milk production "," wheat production "," flood control "," water harvesting ","a","geo");
		insertQuestion(55," Who has the sole right to issue paper currency in India? "," The Government of India "," The Finance Commission "," The Reserve Bank of India "," The Central Bank of India ","c","pol");
		insertQuestion(56," A fall in demand or rise in supply of a commodity- "," increase the price of that commodity "," decrease the price of that commodity "," neutralises the changes in the price "," determines the price elasticity ","b","eco");
		insertQuestion(57," Planning Commission of India was established in ","1947","1950","1951","1949","b","pol");
		insertQuestion(58," How many types of emergencies are envisaged by the Constitution? ","1","2","3","4","c","pol");
		insertQuestion(59," Who calls the Joint Session of the two Houses of the Parliament? "," The President "," The Prime Minister "," The Lok Sabha Speaker "," The Vice-President ","a","pol");
		insertQuestion(60," In which year Gandhiji established Sabarmati Ashram in Gujarat? ","1916","1917","1918","1929","b","pol");
		insertQuestion(61," No confidence Motion against the Union Council of Minister can be initiated "," in the Rajya Sabha only "," in the Lok Sabha only "," in the Lok Sabha and Rajya Sabha "," in the State Assemblies ","b","pol");
		insertQuestion(62," Which constitutional Amendment deleted the Right to Property from the list of Fundamental Rights? "," 42nd Amendment "," 62nd Amendment "," 44th Amendment "," 43rd Amendment ","c","pol");
		insertQuestion(63," Most of the Chola temples were dedicated to "," Vishnu "," Shiva "," Brahma "," Durga ","b","geo");
		insertQuestion(64," Where was the First Session of Indian National Congress held? "," Bombay "," Madras "," Calcutta "," Delhi ","a","pol");
		insertQuestion(65," In the Gupta period;  the largest number of coins were issued in "," gold "," silver "," copper "," iron ","a","his");
		insertQuestion(66," The tax which the kings used to collect from the people in the Vedic period was called- "," Bali "," Vidatha "," Varman "," Kara ","b","his");
		insertQuestion(67," Buddha preached his first sermon at- "," Gaya "," Sarnath "," Pataliputra "," Vaishali  ","b","his");
		insertQuestion(68," Which one of the following is not the result of underground water action? "," Stalactites "," Stalagmites "," Sinkholes "," Fiords ","d","geo");
		insertQuestion(69," Grassland is called pampas in- "," Africa "," South America "," the United Kingdom "," the USA ","b","geo");
		insertQuestion(70," The coastal part of water bodies of the oceans which is structurally part of the mainland of the continents is called "," isthumus "," oceanic ridge "," continental shelf "," continental slope ","c","geo");
		insertQuestion(71," Anemometer is used to measure ?"," wind direction "," wind velocity "," pressure gradient "," wind speed and time ","b","geo");
		insertQuestion(72," Marina Trench is found in "," Atlantic Ocean "," Pacific Ocean "," Indian Ocean "," Arctic Ocean ","b","geo");
		insertQuestion(73," Which cells in our body have the least regenerative power? "," Brain cells "," Muscle cells "," Bone cells "," Liver cells ","a","sci");
		insertQuestion(74," A potato tuber has been cut into two halves. A few drops of iodine solution are placed on the cut surface of one of the halves;  What colour change will be noticed? "," From brow to blue-black "," From brown to orange-red "," From blue to pink "," From pink to blue-green ","a","sci");
		insertQuestion(75," How many values does a human heart have? "," Four "," Three "," Two "," One ","c","sci");
		insertQuestion(76," The cells which are responsible for the production of antibodies are "," red blood cells "," neutrophils "," lymphocytes "," platelets ","c","sci");
		insertQuestion(77," The source of the enzyme; diastase is "," salivary gland "," stomach "," liver "," pancreas ","a","sci");
		insertQuestion(78," Mycoplasma is associated with a disease that affects the organs of "," respiration "," excretion "," reproduction "," digestion ","a","sci");
		insertQuestion(79," Which one of the following has the highest value of specific heat? "," Glass "," Copper "," Lead "," Water ","d","sci");
		insertQuestion(80," The device used for locating submerged objects under sea is. "," sonar "," radar "," laser "," maser ","a","sci");
		insertQuestion(81," The metal whose electrical conductivity is more. Is --"," copper "," aluminum "," silver "," lead ","c","sci");
		insertQuestion(82," What happens to a liquid  when the vapour pressure equals the atmospheric pressure? "," The liquid cools "," The liquid boils "," No change "," The liquid evaporates ","b","sci");
		insertQuestion(83," Seaweeds are important source of "," fluorine "," chlorine "," bromine "," iodine ","d","sci");
		insertQuestion(84," In nuclear reactor;  heavy water is used as "," coolant "," fuel "," moderator "," atomic smasher ","c","sci");
		insertQuestion(85," Which one of the following pairs is correctly matched? "," Tetanus-BCG "," Tuberculosis-ATS "," Malaria-Chloroquin "," Scurvy-Thiamin ","c","sci");
		insertQuestion(86," Tear gas used by the police to disperse the mob contains "," carbon dioxide "," chlorine "," ammonia "," hydrogen sulphide ","b","sci");
		insertQuestion(87," Correct expansion of the term ‘http’ in Internet address is "," higher text transfer protocol "," higher transfer text protocol "," hybrid text transfer protocol "," hypertext transfer protocol ","d","sci");
		insertQuestion(88," Which of the following is a Navaratna PSE? "," Steel Authority of India Ltd. "," MMTC Ltd. "," National Aluminum Company Ltd. "," Oil India Ltd. ","d","pol");
		insertQuestion(89," World AIDS Day is observed on "," 1st January "," 1st April "," 1st September "," 1st December ","d","geo");
		insertQuestion(90," Who was (is) the first lady recipient of Dada Saheb Phalke Award? "," Nargis Dutt "," Uma Devi "," Devika Rani "," Sulochana ","c","his");
		insertQuestion(91," Out of the following artists;  who has written the book -- Meandering pastures of Memories  ?"," Shovana Narayan "," Saroja Vaidyanathan "," Yamini krishnamoorthy "," Geeta Chandran ","a","his");
		insertQuestion(92," Out of the following Indian States;  which State does not have any maritime boundary? "," Gujarat "," Goa "," Rajasthan "," Maharashtra ","c","geo");
		insertQuestion(93," With which brand of product;  is the slogan Just do it  associated? "," Bata "," Power "," Wood land "," Nike ","d","geo");
		insertQuestion(94," With which of the following books is assosiated with Dr. S. Radhakrishnan ?  (a) An Idealist View of Life  (b) Bhagavad Gita (c) Conquest of self  (d) Hindu View of Life","A ; B and C Only","B; C and D only","A; C and D only","A; B and D only","d","pol");
		insertQuestion(95," The Headquarters of International Court of Justice is located in "," Paris (France) "," Geneva (Switzerland) "," New York (USA) "," The Hague (Netherlands) ","d","geo");
		insertQuestion(96," ‘Subroto Cup’ is associated with which game/sports? "," Hockey "," Football "," Basketball "," Badminton ","b","spo");
		insertQuestion(97," The first talkie film in India was "," Raja Harishchandra "," Alam Ara "," Chandida "," Jhansi Ki Rani ","b","his");
		insertQuestion(98," Who holds the record for scoring the highest number of runs (individuals) in One-day Cricket World Cup Tournaments? "," S. Gnaguly "," S. Tendulkar "," B. Lara "," G. Kirsten ","c","spo");
		insertQuestion(99," Which is the field in which Ustad Bismillah Khan has distinguished himself? "," Sitar "," Guitar "," Shehnai "," Hindustani Music (Classical-Vocal) ","c","his");
		insertQuestion(100," Where is the Baba Imambara located? "," Agra "," Lucknow "," Patna "," Allahabad ","b","geo");
		insertQuestion(101," What does the open market operation of the RBI mean? "," Buying and selling shares "," Auctioning of foreign exchange "," Trading is securities "," Transaction in gold ","c","eco");
		insertQuestion(102," Consumer Day is celebrated every year on "," 1st April "," 23rd April "," 15th March "," 5th December ","c","geo");
		insertQuestion(103,"Who was the first prime minister of india ?"," Pt Jawaharlal Nehru"," Lal bahadur Shastri","Sardar bhalabh bhai patel"," Dr Rajendra Prasad","a","pol");
		insertQuestion(104,"Who was the first female prime minister of india ?"," Indira Ghandhi"," Sarojni Naidu","Mayawati"," Prtibha Patil","a","pol");
		insertQuestion(105,"Along with Mo Yan who among the following Hollywood actors has been selected as the member of the Chinese People’s Political Consultative Conference;  one of the houses of the Chinese parliament?","Chow Yun Fat","Bruce Lee","Jackie Chan","Russell Wong","c","pol");
		insertQuestion(106,"Who among the following Solicitor Generals have not been able to successfully complete his five year term in the office? ","L N Sinha ","Dipankar Gupta ","Gopal Subramanium ","G E Vahanvati  ","c","pol");
		insertQuestion(107,"Who among the following has been nominated as the U.S. interior secretary by US President Barack Obama? ","Ken Salazar ","Gale Ann Norton ","Lynn Scarlett ","Sally Jewell  ","d","pol");
		insertQuestion(108,"Rashtriya Bal Sawsthya Karyakram’; a new health initiative ;  has been launched by Sonia Gandhi in: ","Rajasthan ","Delhi ","Maharashtra ","Haryana  ","c","pol");
		insertQuestion(109," Who among the following is the first direct elected President of Czech Republic? ","Vaclav Havel ","Mirek Topolánek ","Václav Klaus ","Milos Zeman  ","d","pol");
		insertQuestion(110," Which among the following Britain-based Olympic champions has announced retirement from all forms of competitive swimming recently? ","Rebecca Adlington ","Janine Belton ","James Kirton ","Sophie Allen  ","a","spo");
		insertQuestion(111," Which among the following private sector banks has partnered with Aircel to launch their mobile banking service called ‘Mobile Money’? ","HDFC Bank ","Axis Bank ","ICICI Bank ","Kotak Mahindra Bank  ","c","eco");
		insertQuestion(112," Name the former Chief Justice of India; who has joined the board of directors of leading stock exchange ; BSE ;  as a public interest director?","K. G. Balakrishnan ","Y.K Sabharwal ","S H Kapadia ","V. N. Khare ","c","pol");
		insertQuestion(113," Ayesha Haroon ;  the renowned journalist ; has died in New York following 4-year long fight with cancer. She belongs to: ","Pakistan ","Afghanistan ","India ","Bangladesh ","a","pol");
		insertQuestion(114," Who among the following has been appointed as the 23rd Governor of Reserve Bank of India? ","Dr Raghuram Ranjan ","Urjit Patel ","Ananda Sinha ","H R Khan   ","a","eco");
		insertQuestion(115," Name the Indian Chess Player who has won Politiken Cup in Denmark. ","Vishwanathan Anand ","P Harikrishna ","Parimanjan Negi ","Abhijit Kunte   ","c","spo");
		insertQuestion(116," Which of the following places is famous for “Chikankari Work”; a traditional embroidery art? ","Lucknow ","Jaipur ","Ajmer ","Dharmavaram   ","a","his");
		insertQuestion(117," The Great Himalayan National Park; which is under consideration to inscribe on the World Heritage list;  is located in which state? ","Haryana ","Himachal Pradesh ","Uttarkhand ","Uttar Pradesh   ","b","geo");
		insertQuestion(118," The memory resident portion of the operating system is called the— "," registry. "," API. "," CMOS. "," kernel. "," d ","sci");
		insertQuestion(119,"The following message generally means: "," a nonsystem floppy has been left in the floppy disk drive. "," the CD drive is not functioning. "," the BIOS is corrupted. "," there is a problem loading a device. "," a","sci");
		insertQuestion(120,"With which sport is Dibeyendu Barua associated? "," Chess "," Football "," Cricket "," Shooting "," a","spo");
		insertQuestion(121,"Dachigam sanctuary is in: "," Uttar Pradesh "," Jammu and Kashmir "," Madhya Pradesh "," Jharkhand "," b ","geo");
		insertQuestion(122,"India is called a mixed economy because of the existence of— 1. Public Sector 2. Private Sector 3. Joint Sector 4. Cooperative Sector "," 1; 4 "," 1; 2 "," 3; 4 "," 2; 4 "," b","eco");
		insertQuestion(123,"The first Indian Nobel Prize winner was: "," Rabindranath Tagore "," C.V.Raman "," Hargovind Khurana "," Mother Teresa ","b","pol");
		insertQuestion(124,"The first lady ‘Gyanpith’ awardee for excellence in literature was: "," Mahasweta Devi "," Mahadevi Verma "," Ashapurna Devi "," Amrita Pritam "," c","pol");
		insertQuestion(125,"According to Dadabhai Naoroji ‘Swaraj’ meant— "," Complete independence "," Self government "," Economic independence "," Political independence "," c","pol");
		insertQuestion(126,"The chemical formula of sulphuric acid is: "," H2SO4 "," HSO4 "," HCI "," H2SO4 "," a","sci");
		insertQuestion(127,"The limb bones of children become bent if there is deficiency of vitamin— "," A "," B1 "," D "," E ","c ","pol");
		insertQuestion(128," Recently (August 2013); Cabinet Committee on Economic Affairs has approved how much percent disinvestment in Indian Oil Corporation? ","10%","25%","15%","20%","a","pol");
		insertQuestion(129," As per the latest estimates; the per capita availability of fruits in India is? ","200 gram ","290 gram ","401 gram ","350 gram   ","a","pol");
		insertQuestion(130," Which one of the following countries; has largest postal network in the world? ","China ","USA ","India ","Russia   ","c","geo");
		insertQuestion(131," mKRISHI; the platform which uses mobile technology to cater to the needs of the rural sector; has been launched by? ","Wipro Technologies ","Tata Consultancy Services ","MS Swaminathan Foundation ","Agrocom ","b","sci");
		insertQuestion(132,"Scientists from NASA have discovered an old star; which they believe could create new planets even now. Name the star: ","TW Hydrae ","Kepler-47b ","LRLL 54361 ","TDRS  ","a","sci");
		insertQuestion(133," The largest planet with no magnetic field is– "," Mercury "," Venus "," Jupiter "," Saturn   ","b","geo");
		insertQuestion(134," Which of the following is the most abundant greenhouse gas in our atmosphere? "," CO2 "," Water vapour "," Methane "," Chlorofluorocarbon   ","b","geo");
		insertQuestion(135," Which of the following statements is/are the characteristic of Tropical rain forest? 1. It is located away from the equator and consists of hundreds of species of trees. 2. Same species rarely grow next to each other. 3. Vegetation is dense; so little light reaches the forest floor. 4. Many vines and epiphytes cling to the branches. "," 1; 2 and 3 "," 2; 3 and 4 "," 2 and 3 "," All of these   ","b","geo");
		insertQuestion(136," Which of the following crops has great adaptability and it can be grown from Siberia to the tropical regions? "," Wheat "," Oat "," Sugarbeet "," Lentil   ","a","geo");
		insertQuestion(137," With regard to fold mountains; which of the following statements is/are correct? 1. Alps in Europe the Rockies of North America; the Andes of South America and the Himalayas of Asia are structural fold mountains.  2. The granitic core of fold mountains is surrounded by metamorphic rocks. 3. Deep gorges; high pyramidal peaks; syntaxian bends; and faulting are the characteristic features of fold mountains. 4. Formation of fold mountains can not be explained with plate tectonics. "," 1 and 4 only "," 2 and 4 only "," 1; 2 and 3 "," 1; 3 and 4   ","c","geo");
		insertQuestion(138," With reference to Savanna Climate which of the following statements is/are correct? 1. This climatic type is bounded by tropical rainforest climate towards the equator and by dry climates towards the poles. 2. Veld plateau of Africa; Llanos of Guyana highlands and the Campos of Brazil represent Savanna Climate. 3. It represents a transitional zone which gets convectional rainfall during the winter; but remains dry during rest of the year. 4. Savanna region is contiguous to tropical rainforest region. "," 1; 2 and 3 "," 2; 3 and 4 "," 2 and 3 only "," 1; 2 and 4   ","d","geo");
		insertQuestion(139," Consider the following statements–  1. Higher latitudes exhibit greater biodiversity normally. However; along the mountain gradients ;  biodiversity is normally greater in the lower altitudes. 2. Salinisation makes some soils impermeable. Which of the statements given above is/are correct? "," 1 only "," 2 only "," Neither 1 nor 2 "," Both 1 and 2  ","b","geo");
		insertQuestion(140," Drip or trickle irrigation technique helps in conserving water through evaporation ;  because– "," the water is provided through underground perforated pipes near the roots. "," it prevents water getting exposed to sunshine during irrigation. "," the perforated pipes lead to efficient usage of water which is directly supplied to the roots. "," None of these  ","a","geo");
		insertQuestion(141," Consider the following statements–  1. Strait of Hormuz connects Persian Gulf to Arabian Sea. 2. Bab-el-Mandeb connects Red Sea to Gulf of Aden. 3. Strait of Kerch connects sea of Azov to Black Sea. 4. Strait of Malacca separates Malay peninsula from Sumatra. Which of these statements is/are incorrect? "," 1; 2 and 3 "," 2; 3 and 4 "," All of the above "," None of these  ","d","geo");
		insertQuestion(142," Select the incorrect statement–  "," Winds do not cross the isobars at right angles because of coriolis force exerted due to rotation of the earth "," The winds blowing from the sub-tropical low-pressure belts towards sub-polar high-pressure belts are known as westerlies "," Jet streams blow from west to east in the upper troposphere ;  embedded in the prevailing westerlies and encircle the globe "," Trade wind blows steadily from sub-tropical high-pressure areas 30°N and towards the equatorial low-pressure belt  ","b","geo");
		insertQuestion(143," Lakshadweep Islands are the product of– "," Reef formation "," Volcanic activity "," Wave action "," Plate collision   ","a","geo");
		insertQuestion(144," Through which one-of the following groups of countries does the Tropic of Capricorn pass? "," Chile ; Paraguay ;  Namibia  ","Bolivia; Zambia;  Fiji ","Peru ;  Angola ;  Zimbabwe "," None of these   ","a","geo");
		insertQuestion(145," Which one of the following country does not border Caspian Sea? "," Iran "," Iraq "," Georgia "," Turkmenistan  ","b","geo");
		insertQuestion(146," Consider the following statements–  I. The Paithan Hydro-electric power project is located on the river Cauvery. II. The Salal Project is located on the river Jhelum. III. The Ukai is a hydro-electric power project. IV. The Balimela is a thermal power project V. Srisailam thermal power project is located in Andhra Pradesh. Which of the statements given above is/are correct? "," I only "," III only "," V only "," None of these  ","d","geo");
		insertQuestion(147," With respect to ‘Indian agriculture’ consider the following statements– 1. Agriculture and Allied sectors contribute nearly 14.4% to GDP of India. 2. Nearly 55% of total cropped area is dependent on rainfall. 3. C entral Institute of Horticulture is located in Nagaland. 4. The Kisan Credit Card scheme was introduced in August; 1998 with major share of crop loans being routed through it. Which of these statements is/are incorrect?"," Only 1 "," Only 2 "," Only 3 and 4 "," None of these  ","d","geo");
		insertQuestion(148," Consider the following statements–  1. Power development in India started with commissioning of electricity supply at Darjeeling during 1897; followed by the commissioning of a hydropower station at Sivasamudram in Karnataka during 1902. II. The Power Finance Corporation Limited (PFC) is a leading power sector financial institution and a Non-Banking Financial Company providing fund and non-fund based support for the development of the Indian Power Sector. Which of the above statement is/are correct? "," I only "," II only "," Both I and II "," Neither I nor II  ","c","geo");
		insertQuestion(149," Which one of the following statements is/are correct? 1. West Bengal has higher percentage of net sown area compared to Andhra Pradesh. II. The soil of most of Andhra Pradesh is laterite. "," I only "," II only "," Both I and II "," Neither I nor II   ","a","geo");
		insertQuestion(150," Consider the following statements–  1. The first telegraph line in India was laid between Kolkata and Diamond Harbour. 2. The first Export Processing Zone in India was set up in Kandla. Which of these statements is/are correct? "," 1 only "," 2 only "," Both 1 and 2 "," Neither 1 nor 2   ","c","geo");
		insertQuestion(151," Which one of the following pairs is not correctly matched? ","Aravalli – Pre-Cambrian relict mountain ","Garo; Khasi Jaintia and Mikir hills – Structurally parts of the peninsular plateaus ","Chola; Changla and Bomdila – Outer Himalayan passes ","Guru Shikhar – Highest peak of the Aravalli range   ","c","geo");
		insertQuestion(152," Madhapur is famous for–  "," largest wind farms cluster. "," largest solar plant in India to sterilise milk cans. "," newly discovered oil field near Bhuj in Gujarat. "," None of these   ","b","geo");
		insertQuestion(153," India attained ‘Dominion Status’ on— "," 15th January; 1947"," 15th August; 1947"," 15th August; 1950"," 15th October; 1947","B","pol");
		insertQuestion(154," Despotism is possible in a— "," One party state "," Two party state "," Multi party state "," Two and multi party state "," A","pol");
		insertQuestion(155," Marx belonged to— "," Germany "," Holland "," France "," Britain "," A","pol");
		insertQuestion(156," Which one of the following is the guardian of Fundamental Rights ? "," Legislature "," Executive "," Political parties "," Judiciary "," D","pol");
		insertQuestion(157," Sarkaria Commission was concerned with— "," Administrative Reforms "," Electoral Reforms "," Financial Reforms "," Centre-State relations "," D","pol");
		insertQuestion(158," The Speaker of the Lok Sabha has to address his/her letter of resignation to— "," Prime Minister of India "," President of India "," Deputy Speaker of Lok Sabha "," Minister of Parliamentary Affairs "," C","pol");
		insertQuestion(159," A want becomes a demand only when it is backed by the— "," Ability to purchase "," Necessity to buy "," Desire to buy "," Utility of the product "," D","eco");
		insertQuestion(160," The terms ‘Micro Economics’ and ‘Macro Economics’ were coined by— "," Alfred Marshall "," Ragner Nurkse "," Ragner Frisch "," J.M. Keynes "," C","eco");
		insertQuestion(161," During period of inflation ;  tax rates should—"," Increase "," Decrease "," Remain constant "," Fluctuate ","A","eco");
		insertQuestion(162," Which is the biggest tax paying sector in India ? "," Agriculture sector "," Industrial sector "," Transport sector "," Banking sector "," D","eco");
		insertQuestion(163,"“Economics is what it ought to be.”—This statement refers to— "," Normative economics "," Positive economics "," Monetary economics "," Fiscal economics "," A","eco");
		insertQuestion(164,"The excess of price a person is to pay rather than forego the consumption of the commodity is called— "," Price "," Profit "," Producers’ surplus "," Consumers’ surplus "," C","eco");
		insertQuestion(165,"Silver halides are used in photographic plates because they are— "," Oxidised in air "," Soluble in hyposolution "," Reduced by light "," Totally colourless "," B","sci");
		insertQuestion(166,"Tetra Ethyl Lead (TEL) is— "," A catalyst in burning fossil fuel "," An antioxidant "," A reductant "," An antiknock compound "," D","sci");
		insertQuestion(167,"Curie point is the temperature at which— "," Matter becomes radioactive "," A metal loses magnetic properties "," A metal loses conductivity "," Transmutation of metal occurs "," D","sci");
		insertQuestion(168,"The isotope used for the production of atomic energy is— "," U-235 "," U-238 "," U-234 "," U-236 "," A","sci");
		insertQuestion(169,"The acceleration due to gravity at the equator— "," Is less than that at the poles "," Is greater than that at the poles "," Is equal to that at the poles "," Does not depend on the earth’s centripetal acceleration "," A","sci");
		insertQuestion(170," Which of the following is not a nucleon ? "," Proton "," Neutron "," Electron "," Positron "," C","sci");
		insertQuestion(171," The material used in the manufacture of lead pencil is— "," Graphite "," Lead "," Carbon "," Mica "," A","sci");
		insertQuestion(172," Angle of friction and angle of repose are— "," Equal to each other "," Not equal to each other "," Proportional to each other "," None of the above "," A","sci");
		insertQuestion(173," Processor’s speed of a computer is measured in— "," BPS "," MIPS "," Baud "," Hertz "," D","sci");
		insertQuestion(174," ‘C’ language is a— "," Low level language "," High level language "," Machine level language "," Assembly level language "," B","sci");
		insertQuestion(175," What happens to a person who receives the wrong type of blood ? "," All the arteries constrict "," All the arteries dialates "," The RBCs agglutinate "," The spleen and lymphnodes deteriorate "," C","sci");
		insertQuestion(176," NIS stands for— "," National Infectious diseases Seminar "," National Irrigation Schedule "," National Immunisation Schedule "," National Information Sector "," C","geo");
		insertQuestion(177," If all bullets could not be removed from gun shot injury of a man ;  it may cause poisoning by— "," Mercury "," Lead "," Iron "," Arsenic ","B","sci");
		insertQuestion(178," Ringworm is a ……… disease. "," Bacterial "," Protozoan "," Viral "," Fungal "," D","sci");
		insertQuestion(179," Pituitary gland is situated in— "," The base of the heart "," The base of the brain "," The neck "," The abdomen "," B","sci");
		insertQuestion(180," Who discovered cement ? "," Agassit "," Albertus Magnus "," Joseph Aspdin "," Janseen "," C","sci");
		insertQuestion(181," According to RBI’s Report on the trend and progress of banking ;  the Non-performing Assets (NPA’s) in India for 2008-09 for Indian Banks in 2008 have stood at— "," 2•3 per cent "," 2•6 per cent "," 3•5 per cent "," 5•2 per cent ","B","eco");
		insertQuestion(182," Windows 7; the latest operating system from Microsoft Corporation has ……… Indian languages fonts. ","14","26","37","49"," B","sci");
		insertQuestion(183," TRIPS and TRIMS are the terms associated with— "," IMF "," WTO "," IBRD "," IDA "," B","eco");
		insertQuestion(184," A Presidential Ordinance can remain in force— "," For three months "," For six months "," For nine months "," Indefinitely "," B","pol");
		insertQuestion(185," Which of the following Indonesian regions was a victim of massive earthquake in 2004 ? "," Irian Jaya "," Sumatra "," Kalibangan "," Java "," B","pol");
		insertQuestion(186," The first nonstop airconditioned ‘DURANTO’ train was flagged off between— "," Sealdah—New Delhi "," Mumbai—Howrah "," Bangalore—Howrah "," Chennai—New Delhi "," A","pol");
		insertQuestion(187," Which among the following agencies released the report; Economic Outlook for 2009-10 ? "," Planning Commission "," PM’s Economic Advisory Council "," Finance Commission "," Reserve Bank of India "," B","eco");
		insertQuestion(188," India and U.S. have decided to finalize agreements related to which of the following ? "," Trade and Investment "," Intellectual Property "," Traditional Knowledge "," All of the above "," D","pol");
		insertQuestion(189," Which one of the following states does not form part of Narmada River basin ? "," Madhya Pradesh "," Rajasthan "," Gujarat "," Maharashtra "," B","geo");
		insertQuestion(190," Which of the following countries has recently become the third largest market for Twitter ? "," China "," India "," Brazil "," Indonesia "," A","geo");
		insertQuestion(191," The exchange of commodities between two countries is referred as— "," Balance of trade "," Bilateral trade "," Volume of trade "," Multilateral trade "," B","eco");
		insertQuestion(192," Soil erosion on hill slopes can be checked by— "," Afforestation "," Terrace cultivation "," Strip cropping "," Contour ploughing "," A","geo");
		insertQuestion(193," Who coined the word Geography ? "," Ptolemy "," Eratosthenese "," Hecataus "," Herodatus "," B","geo");
		insertQuestion(194," Which of the following is called the ‘ecological hot spot of India’ ? "," Western Ghats "," Eastern Ghats "," Western Himalayas "," Eastern Himalayas "," A","geo");
		insertQuestion(195," The art and science of map making is called— "," Remote Sensing "," Cartography "," Photogrammetry "," Mapping "," B","geo");
		insertQuestion(196," The age of the Earth can be determined by— "," Geological Time Scale "," Radio-Metric Dating "," Gravity method "," Fossilization method "," B","geo");
		insertQuestion(197," The monk who influenced Ashoka to embrace Buddhism was— "," Vishnu Gupta "," Upa Gupta "," Brahma Gupta "," Brihadratha "," B","his");
		insertQuestion(198," The declaration that Democracy is a Government ‘of the people ;  by the people; for the people’ was made by— "," George Washington "," Winston Churchill "," Abraham Lincoln "," Theodore Roosevelt "," C","pol");
		insertQuestion(199," The Lodi dynasty was founded by— "," Ibrahim Lodi "," Sikandar Lodi "," Bahlol Lodi "," Khizr Khan "," C","his");
		insertQuestion(200,"Harshavardhana was defeated by— "," Prabhakaravardhana "," Pulakesin II "," Narasimhavarma Pallava "," Sasanka "," B","his");
		insertQuestion(201,"Who among the following was an illiterate ? "," Jahangir "," Shah Jahan "," Akbar "," Aurangazeb "," C","his");
		insertQuestion(202,"Which Governor General is associated with Doctrine of Lapse ? "," Lord Ripon "," Lord Dalhousie "," Lord Bentinck "," Lord Curzon "," B","his");
		insertQuestion(203," Recently (August 2013); Cabinet Committee on Economic Affairs has approved how much percent disinvestment in Indian Oil Corporation? ","10%","25%","15%","20%","a","eco");
		insertQuestion(204," As per the latest estimates; the per capita availability of fruits in India is? ","200 gram ","290 gram ","401 gram ","350 gram   ","a","eco");
		insertQuestion(205," Which one of the following countries; has largest postal network in the world? ","China ","USA ","India ","Russia   ","c","geo");
		insertQuestion(206," mKRISHI; the platform which uses mobile technology to cater to the needs of the rural sector; has been launched by? ","Wipro Technologies ","Tata Consultancy Services ","MS Swaminathan Foundation ","Agrocom ","b","sci");
		insertQuestion(207,"Scientists from NASA have discovered an old star; which they believe could create new planets even now. Name the star: ","TW Hydrae ","Kepler-47b ","LRLL 54361 ","TDRS  ","a","sci");
		insertQuestion(208," The Ex-officio Secretary of NDC is— "," Secretary of Finance Ministry "," General Secretary of Lok Sabha "," Secretary of Planning Commission "," Vice Chairman of Planning Commission ","c ","pol");
		insertQuestion(209," For charters and cargo services; what is the new FDI ceiling announced by the government ? ","100%","74%","26%","49%","b","eco");
		insertQuestion(210," In National Mineral Policy (1993) which mineral was allowed for having investment from private sector— "," Coal "," Iron "," Gold "," Platinum ","a","eco");
		insertQuestion(211," The share of road transport in total transport of the country is— ","20%","40%","60%","80%","d","eco");
		insertQuestion(212," Minimum Support Price is decided by— "," ICAR "," State Government "," Ministry of Agriculture "," CACP ","d ","eco");
		insertQuestion(213," Which percentage of Central Taxes have been recommended by the 12th Finance Commission to be transferred to States ? "," 28·5% "," 29·5% "," 30·5% "," 31·5% ","c","pol");
		insertQuestion(214," Which state possesses the maximum percentage of SC population ? "," U.P. "," M.P. "," Kerala "," Punjab","d","pol");
		insertQuestion(215," Government has decided to cover all districts of the country in National Rural Employment Guarantee Programme (NREGP)— "," Upto January 1; 2008 "," Upto March 31; 2008 "," w.e.f. April 1; 2008 "," w.e.f. April 1; 2009","c","pol");
		insertQuestion(216," What is ‘NIKKEI’ ? "," Share Price Index of Tokyo Share Market "," Name of Japanese Central Bank "," Japanese name of Country’s Planning Commission "," Foreign Exchange Market of Japan ","a","eco");
		insertQuestion(217," Which statement is correct for Indian Planning Commission ? "," It is not defined in Indian Constitution "," Members and Vice-Chairman of it do not have fixed working duration "," Members do not require any minimum education "," All of these","d","eco");
		insertQuestion(218," Which State of the following has not yet adopted VAT (Value Added Tax) ? "," Tamil Nadu "," Uttar Pradesh "," Gujarat "," None of the above ","d ","eco");
		insertQuestion(219," Service Tax revenue collection for 2008–09 (Budget estimates) was proposed at— "," Rs. 64460 crore "," Rs. 52603 crore "," Rs. 50200 crore "," Rs. 74460 crore","a","eco");
		insertQuestion(220," NABARD was established on the recommendation of— "," Public Account Committee "," Shivaraman Committee "," Narsimham Committee "," None of these","b","eco");
		insertQuestion(221," Sampurna Gramin Rojgar Yojana was launched on— "," 1st April; 2001 "," 25th Sept; 2001 "," 30th Sept; 2001 "," No scheme of such title has yet launched","b","eco");
		insertQuestion(222," Which company is providing mobile service with name ‘Cell One’ to the consumers ? "," MTNL "," BSNL "," Reliance Infocom "," Bharti Tele","b","eco");
		insertQuestion(223," VAT is imposed— "," Directly on consumer "," On final stage of production "," On first stage of production "," On all stages between production and final sale","d","eco");
		insertQuestion(224," The newly appointed person as Chairman of CBDT is— "," S. Sridhar "," S. S. N. Moorti "," Rajiv Chandrashekhar "," Venugopal Dhoot ","b","eco");
		insertQuestion(225," Kutir Jyoti scheme is associated with— "," Promoting cottage industry in villages "," Promoting employment among rural unemployed youth "," Providing electricity to rural families living below the poverty line "," All of these","c","eco");
		insertQuestion(225," Novelis has been acquired and merged with— "," Tata Steels "," SAIL "," HINDALCO "," Jindal Steels ","c","eco");
		insertQuestion(226," OTCEI is— "," Atomic Submarine of China "," Economic Policy of USA "," An Indian Share Market "," A Defence Research Organisation","c","eco");
		insertQuestion(227," Foreign Trade Policy 2009-10 document fixes the export target for 2009-10 as— "," $ 140 billion "," $ 175 billion "," $ 150 billion "," $ 200 billion ","d","eco");
		insertQuestion(228," Gross Budgetary Support (GBS) for 2008–09 as per document of 11th plan stands at Rs. 228725 crore but in budget proposals for 2008–09 it was raised to— "," Rs. 223386 crore "," Rs. 243386 crore "," Rs. 263386 crore "," Rs. 28456 crore  ","b","eco");
		insertQuestion(229," The base year of Industrial Production Index is being shifted from 1993-94 to— "," 2004-05 ","1999"," 2000-01 "," 1999-2000 ","d","eco");
		insertQuestion(230," In Interim Budget proposals for 2009–10 ;  which of the following gives 22% contribution in revenue collection of union government ?","Non tax revenue"," Income Tax "," Excise "," Corporation Tax ","c","eco");
		insertQuestion(231," The base year of present Consumer Price Index (CPI) for Urban Non-Manual Employees (CPI—UNME) is— "," 1980-81 "," 1981-82 "," 1982-83 "," 1984-85 "," d","eco");
		insertQuestion(232," Article 17 of the constitution of India provides for "," equality before law. "," equality of opportunity in matters of public employment. "," abolition of titles. "," abolition of untouchability ","d","pol");
		insertQuestion(233," Article 370 of the constitution of India provides for "," temporary provisions for Jammu & Kashmir. "," special provisions in respect of Nagaland. "," special provisions in respect of Manipur. "," provisions in respect of financial emergency ","a","pol");
		insertQuestion(234," How many permanent members are there in Security Council? "," Three "," Five "," Six "," Four ","b","pol");
		insertQuestion(235," The United Kingdom is a classic example of a/an "," aristocracy "," absolute monarchy "," constitutional monarchy "," polity. ","c","pol");
		insertQuestion(236," Social Contract Theory was advocated by "," Hobbes; Locke and Rousseau. "," Plato; Aristotle and Hegel. "," Mill; Bentham and Plato. "," Locke; Mill and Hegel. ","a","pol");
		insertQuestion(237," The Speaker of the Lok Sabha is elected by the "," President "," Prime Minister. "," Members of both Houses of the Parliament. "," Members of the Lok Sabha. ","d","pol");
		insertQuestion(238," Who is called the Father of History? "," Plutarch "," Herodotus "," Justin "," Pliny ","b","pol");
		insertQuestion(239," The Vedas are known as "," Smriti. "," Sruti. "," Jnana. "," Siksha. ","b","his");
		insertQuestion(240," The members of Estimate Committee are "," elected from the Lok Sabha only.  "," elected from the Rajya Sabha only. "," elected from both the Lok Sabha and the Rajya Sabha. "," nominated by the Speaker of the Lok Sabha. ","a","pol");
		insertQuestion(241," Who is the chief advisor to the Governor? "," Chief Justice of the Supreme Court. "," Chief Minister. "," Speaker of the Lok Sabha. "," President. ","b","eco");
		insertQuestion(242," Foreign currency which has a tendency of quick migration is called "," Scarce currency. "," Soft currency. "," Gold currency. "," Hot currency. ","d","eco");
		insertQuestion(243," Which of the following is a better measurement of Economic Development? "," GDP "," Disposable income "," NNP "," Per capita income ","a","eco");
		insertQuestion(244," In India; disguised unemployment is generally observed in "," the agriculture sector. "," the factory sector. "," the service sector. "," All these sectors. ","a","eco");
		insertQuestion(245," If the commodities manufactured in Surat are sold in Mumbai or Delhi then it is "," Territorial trade. "," Internal trade. "," International trade. "," Free trade. ","b","eco");
		insertQuestion(246," The famous slogan GARIBI HATAO (Remove Poverty) was launched during the "," First Five-Year Plan (1951-56) "," Third Five-Year Plan (1961-66) "," Fourth Five-Year Plan (1969-74) "," Fifth Five-Year Plan (1974-79) ","c","eco");
		insertQuestion(247," Bank Rate refers to the interest rate at which "," Commercial banks receive deposits from the public. "," Central bank gives loans to Commercial banks. "," Government loans are floated. "," Commercial banks grant loans to their customers. ","b","eco");
		insertQuestion(248," All the goods which are scare and limited in supply are called "," Luxury goods. "," Expensive goods. "," Capital goods. "," Economic goods. ","d","eco");
		insertQuestion(249," The theory of monopolistic competition is developed by "," E.H.Chamberlin "," P.A.Samuelson "," J.Robinson "," A.Marshall ","a","eco");
		insertQuestion(250," Smoke is formed due to "," solid dispersed in gas. "," solid dispersed in liquid. "," gas dispersed in solid. "," gas dispersed in gas. ","a","sci");
		insertQuestion(251," Which of the following chemical is used in photography? "," Aluminum hydroxide "," Silver bromide "," Potassium nitrate "," Sodium chloride. ","b","sci");
		insertQuestion(252," Gober gas (Biogas) mainly contains  "," Methane. "," Ethane and butane. "," propane and butane. "," methane; ethane; propane and propylene. ","a","sci");
		insertQuestion(253," Preparation of Dalda or Vanaspati ghee from vegetable oil utilises the following process "," Hydrolysis "," Oxidation "," Hydrogenation "," Ozonoloysis ","b","sci");
		insertQuestion(254," Which colour is the complementary colour of yellow? "," Blue "," Green "," Orange "," Red ","b","sci");
		insertQuestion(255," During washing of cloths; we use indigo due to its "," better cleaning action. "," proper pigmental composition. "," high glorious nature. "," very low cost. ","a","sci");
		insertQuestion(256," Of the following Indian satellites; which one is intended for long distance telecommunication and for transmitting TV programmes? "," INSAT-A "," Aryabhata "," Bhaskara "," Rohini ","a","sci");
		insertQuestion(257," What is the full form of AM regarding radio broadcasting? "," Amplitude Movement "," Anywhere Movement "," Amplitude Matching "," Amplitude Modulation. ","d ","sci");
		insertQuestion(258," Who is the author of Gandhi ji- favorite Bhajan Vaishnava jana to tene kahiye? "," Purandar Das "," Shyamal Bhatt  "," Narsi Mehta "," Sant Gyaneshwar ","c","his");
		insertQuestion(259," Which one of the following is not a mosquito borne disease? "," Dengu fever "," Filariasis "," Sleeping sickness "," Malaria ","d","sci");
		insertQuestion(260," What is the principal ore of aluminium? "," Dolomite "," Copper "," Lignite "," Bauxite ","d","sci");
		insertQuestion(261," Which country is the facilitator for peace talks between the LTTE and the Sri Lankan Government? "," The US "," Norway "," India "," The UK ","a","pol");
		insertQuestion(262," The highest body which approves the Five-Year Plan in India is the "," Planning Commission "," National Development Council "," The Union Cabinet "," Finance Ministry ","c","pol");
		insertQuestion(263," Ceteris Paribus is Latin for  ","all other things variable ","other things increasing","other things being equal","all other things decreasing","c","pol");
		insertQuestion(264," Who has been conferred the Dada Saheb Phalke Award (Ratna) for the year 2007? "," Dev Anand "," Rekha "," Dilip Kumar "," Shabana Azmi ","c","his");
		insertQuestion(265," Purchasing Power Parity theory is related with "," Interest Rate. "," Bank Rate. "," Wage Rate. "," Exchange Rate. ","a","eco");
		insertQuestion(266," India's biggest enterprise today is "," the Indian Railways. "," the Indian Commercial Banking System. "," the India Power Sector. "," the India Telecommunication System. ","c","eco");
		insertQuestion(267," The official agency responsible for estimating National Income in India is "," Indian Statistical Institute. "," Reserve Bank of India. "," Central Statistical Organisation. "," National Council for Applied Economics and Research. ","d","eco");
		insertQuestion(268," Which of the following has the sole right of issuing currency (except one rupee coins and notes) in India? "," The Governor of India "," The Planning Commission "," The State Bank of India "," The Reserve Bank of India ","d","eco");
		insertQuestion(269," In the budget figures of the Government of India the difference between total expenditure and total receipt is called. "," Fiscal deficit "," Budget deficit "," Revenue deficit "," Current deficit ","a","eco");
		insertQuestion(270," Fascism believes in  "," Peaceful change "," Force "," Tolerance "," Basic Rights for the individual ","d","pol");
		insertQuestion(271," Which is the most essential function of an entrepreneur? "," Supervision "," Management "," Marketing "," Risk bearing ","a","eco");
		insertQuestion(272," M. Damodaran is the "," Chairman; Unit Trust of India. "," Deputy Governor of Reserve Bank of India. "," Chairman; Securities and Exchange Board of India. "," Chairman; Life Insurance Corporation of India. ","c","eco");
		insertQuestion(273," What is the name of the Light Combat Aircraft developed by India indigenously? "," BrahMos "," Chetak "," Astra "," Tejas ","d","geo");
		insertQuestion(274," Who is the Prime Minister of Great Britain? "," Tony Blair "," Jack Straw "," Robin Cook "," Gordon Brown. ","d","pol");
		insertQuestion(275," The 2010 World Cup Football Tournament will be held in "," France. "," China. "," Germany. "," South Africa. ","b ","spo");
		insertQuestion(277," Who is the present Chief Election Commissioner of India? "," Navin Chawla "," N.Gopalswamy "," T.S.krishnamoorty "," B.B.Tandon ","a","pol");
		insertQuestion(278," What was the original name of Nurjahan? "," Jabunnisa "," Fatima Begum "," Mehrunnisa "," Jahanara ","d","his");
		insertQuestion(279," Which of the following pairs is not correctly matched ? "," Lord Dallhousie- Doctrine of Lapse "," Lord Minto- Indian Councils Act; 1909 "," Lord Wellesley- Subsidiary Alliance "," Lord Curzon- Vernacular Press Act; 1878 ","d","his");
		insertQuestion(280," The province of Bengal was partitioned into two parts in 1905 by "," Lord Lytton.  "," Lord Ripon. "," Lord Dufferin. "," Lord Curzon. ","b","his");
		insertQuestion(281," The essential features of the Indus Valley Civilization was "," worship of forces of nature. "," organized city life. "," pastoral farming. "," caste society. ","a","his");
		insertQuestion(282," Name the capital of Pallavas. "," Kanchi. "," Vattapi. "," Trichnapalli. "," Mahabalipuram. ","b","his");
		insertQuestion(283," The Home Rule League was started by "," M.K.Gandhi "," B.G.Tilak "," Ranade "," K.T.Telang ","c","his");
		insertQuestion(284," The Simon Commission was boycotted by the Indians because "," it sought tocurb civil liberties. "," it proposed to partition India. "," it was an all-white commission Indian representation. "," it proposed measures for nationalism. ","d","his");
		insertQuestion(285," Storm of gases are visible in the chamber of the Sun during "," Cyclones "," Anti-cyclones "," Lunar-eclipse "," Solar eclipse. ","c","sci");
		insertQuestion(286," The Indian Councils Act of 1990 is associated with "," The Montagu Decleration. "," The Montagu- Chelmsford Reforms. "," The Morley-Minto Reforms. "," The Rowlatt Act. ","d","his");
		insertQuestion(287," Of all micro-organisms"," the most adaptable and versatile are "," Viruses "," Bacteria "," Algae d) Fungi ","a","eco");
		insertQuestion(288," What is an endoscope? "," It is an optical instrument used to see inside the alimentary canal "," it is device which is fitted on the chest of the patient to regularize the irregular heart beats "," It is an instrument used for examining ear disorders  "," It is an instrument for recording electrical signals produced by the human muscles.  ","a","sci");
		insertQuestion(289," The disease in which the sugar level increase is known as "," Diabetes mellitus "," Diabetes insipidus "," Diabetes imperfectus "," Diabetes sugarensis ","a","sci");
		insertQuestion(290," The President of India is elected by "," members of both Houses of the Parliament. "," members of both houses of Parliament of State Legislatures. "," members of both Houses of the State Legislative Assemblies. "," Elected members of both Houses of the Parliament and members of Legislative Assemblies. ","d","pol");
		insertQuestion(291," The nitrogen present in the atmosphere is "," of no use to plants. "," injurious of plants. "," directly utilized by plants. "," utilized through micro-organisms. ","a","sci");
		insertQuestion(292," Diamond and Graphite are "," allotropes "," isomorphous "," isomers "," isobars ","b","sci");
		insertQuestion(293," Kayak is kind of "," tribal tool. "," boat. "," ship. "," weapon. ","b","geo");
		insertQuestion(294," Which of the following has the highest calorific value? "," Carbohydrates "," fats "," Proteins "," Vitamins. ","a","sci");
		insertQuestion(295," Rotation of crops means "," growing of different crops in succession to maintain soil fertility. "," some crops are growing again and again. "," two or more crops are grown simultaneously to increase productivity. "," None of these. ","a","geo");
		insertQuestion(296," Suez Canal connects "," Pacific Ocean and Atlantic Ocean. "," Mediterranean Sea and Red Sea. "," Lake Huron and Lake Erie. "," Lake Erie and Lake Ontario. ","b ","geo");
		insertQuestion(297," Which of the following ports has the largest hinterland? "," Kandla "," Kochi "," Mumbai "," Vishkhapatnam. ","d","geo");
		insertQuestion(298,"Slash and Burn agriculture- is the name given to "," method of potato cultivation. "," process of deforestation. "," mixed framing. "," shifting cultivation. ","a","geo");
		insertQuestion(299," The main reason for deforestation in Asia is "," excessive fuel wood collection. "," excessive soil erosion. "," floods. "," construction of roads. ","d","geo");
		insertQuestion(300," Recharging of water table depends on "," amount of rainfall. "," relief of the area. "," vegetation of the area. "," amount of percolation. ","a","geo");
		
		
		}
		catch(SQLException ex)
		{
			Log.v("INFO", "DatabaseOpenhelper::populateDatabase -- ERROR while populating database----");
			System.out.print("ERROR while populating database");
		}
		Log.v("INFO", "DatabaseOpenhelper::populateDatabase ---- END");
	}
	
	private class DatabaseOpenHelper extends SQLiteOpenHelper 
	{
		
		public DatabaseOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			Log.v("INFO", "DatabaseConnector-- DatabaseOpenHelper CTOR---- START");
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{	
			Log.v("INFO", "DatabaseOpenhelper::OnCreate ---- START");
			String query = "CREATE TABLE QUIZ (question_id integer primary key , question TEXT, a TEXT, b TEXT,c TEXT, d TEXT, answer TEXT, category TEXT);";
			try{	
				Log.v("INFO", "DatabaseOpenhelper::OnCreate ---- before executing query");
				db.execSQL(query);
				Log.v("INFO", "DatabaseOpenhelper::OnCreate ---- AFTER executing query");
				//populateDatabase();
			}
			catch(SQLException ex)
			{
				Log.v("EXCEPTION", "DatabaseOpenhelper::OnCreate  ");
			}
			Log.v("INFO", "DatabaseConnector-- OnCreate ---- END");
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
	
		}
		
		
	}
}
