import java.io.*;
import java.util.Scanner;

public class LocNotManager {
	// Load notifications from file. Assume format is correct. The notifications are
	// indexed by latitude then by longitude.
	public static Map<Double, Map<Double, LocNot>> load(String fileName) {
		
		Map<Double, Map<Double, LocNot>> my_map= new BST<Double, Map<Double, LocNot>>();
		
		File f =null;
		Scanner read=null;
		
		try {
	 f =new File(fileName);
		
 read=new Scanner(f);
		
 String str="";
 
		while(read.hasNext()) {
			
			str=read.nextLine();
			
			String[] str_array=str.split("\t");
			
			

				LocNot L=	new LocNot(str_array[4],Double.parseDouble(str_array[0]) ,Double.parseDouble(str_array[1]),Integer.parseInt(str_array[2]),Integer.parseInt(str_array[3]) );
				
			Map<Double, LocNot > inside= new BST <Double, LocNot>();
			
			if(!my_map.find(Double.parseDouble(str_array[0]))){
			inside.insert(Double.parseDouble(str_array[1]), L);
			my_map.insert(Double.parseDouble(str_array[0]),inside);
		
		}
			else {
				
			
				
				my_map.retrieve().insert(Double.parseDouble(str_array[1]), L);
			}
		
		
			
		}
		}
		
		
		catch(Exception e) {
		
		}
		read.close();
		return my_map;
	}
		
	// Save notifications to file.
	public static void save(String fileName, Map<Double, Map<Double, LocNot>> nots) {
		File f =null;
		
		FileOutputStream os =null;
		PrintWriter pw=null;
		List <LocNot> the_Not= getAllNots( nots);
		
		//Pair <Double, Map<Double, LocNot>> the_new= new Pair <Double, Map<Double, LocNot>>(nots.Key,nots.data );
		
		LocNot loc=null;
	
		
		
		try {
			
			f=new File(fileName);
			os = new FileOutputStream(f);
			 pw=new PrintWriter(os) ;
			
			 the_Not.findFirst();
			 
			while(!the_Not.last()) {
				loc=the_Not.retrieve();
				
				pw.println(loc.toString());
				
				the_Not.findNext();
			}
			
			loc=the_Not.retrieve();
			
			pw.println(loc.toString());
			
			
		}

		catch(Exception e) {
			
		}
	
		
		
		pw.close();
	}

	//private static void rec_OUTERsave ( Map<Double, Map<Double, LocNot>> nots ) {
		
		
	
	// Return all notifications sorted first by latitude then by longitude.
	public static List<LocNot> getAllNots(Map<Double, Map<Double, LocNot>> nots) {//DONE
		
		
		List<Pair<Double, Map<Double, LocNot>>> All_Map =nots.getAll();
		
	//	List <Pair<Double, LocNot>> The_Inner_map= null;
		
		List<LocNot> All_N = new LinkedList<LocNot>();
		
		if(All_Map.empty()) //CHECK IF THE LIST IS EMPTY!!!!!!
			return All_N;
		
		
		
		
		All_Map.findFirst();
		
		while(!All_Map.last() ) {
			List <Pair<Double, LocNot>> The_Inner_map=new LinkedList<Pair<Double, LocNot>>();			
			Pair<Double, Map<Double, LocNot>> WERID=  All_Map.retrieve();
			
			The_Inner_map= 	 WERID.second.getAll();
			
			if(!The_Inner_map.empty()) {//CHECK IF THE LIST IS EMPTY!!!!!!
				
			The_Inner_map.findFirst();
			while(!The_Inner_map.last()) {
				
				Pair<Double, LocNot> WERID_2=	The_Inner_map.retrieve();
				All_N.insert(WERID_2.second);
				
				The_Inner_map.findNext();
			}
			
			Pair<Double, LocNot> WERID_2=	The_Inner_map.retrieve();
			All_N.insert(WERID_2.second);
			}
			All_Map.findNext();
			
		}
		
		Pair<Double, Map<Double, LocNot>> WERID=  All_Map.retrieve();
		List <Pair<Double, LocNot>> The_Inner_map=new LinkedList<Pair<Double, LocNot>>();
		The_Inner_map= 	 WERID.second.getAll();
		
		if(!The_Inner_map.empty()) {
			
			
			The_Inner_map.findFirst();	
			
		while(!The_Inner_map.last()) {//CHECK IF THE LIST IS EMPTY!!!!!!
			
			Pair<Double, LocNot> WERID_2=	The_Inner_map.retrieve();
			All_N.insert(WERID_2.second);
			
			The_Inner_map.findNext();
		}
		
		Pair<Double, LocNot> WERID_2=	The_Inner_map.retrieve();
		All_N.insert(WERID_2.second);
		
		}
		return All_N;
	}

	
	
	
	// Add a notification. Returns true if insert took place, false otherwise.
	public static boolean addNot(Map<Double, Map<Double, LocNot>> nots, LocNot not) {//DONE

		
		if(not==null)
			return false;
		
	double lat=not.getLat();

	  
	if(nots.find(lat)) {
		
		Map<Double, LocNot> inside=	nots.retrieve();
		
		if(!inside.find(not.getLng())) {
			
		inside.insert(not.getLng(), not);
		
			return true;
	}
		else {
			return false;
			
		}
	
	}
	else {
		if(!nots.find(lat)) {
		
		Map<Double, LocNot> inside=	new BST<Double, LocNot>() ;
		if(!nots.find(not.getLng())) {
		inside.insert(not.getLng(), not);
		
		nots.insert(lat, inside);
		return true;
		}
		else {
			return false;
			}
		}
		return false;
	}

	//	nots.insert(not.getLat(), );

	}

	// Delete the notification at (lat, lng). Returns true if delete took place, false otherwise.
	public static boolean delNot(Map<Double, Map<Double, LocNot>> nots, double lat, double lng) {//DONE
		
		if(nots.empty())
			return false;
		
		
		if(nots.find(lat)) {
			
			Map<Double, LocNot> inside=	nots.retrieve();
			if(inside.find(lng)) {
				inside.remove(lng);
				
				if(inside.empty())
					nots.remove(lat);
				
				return true;
			}
			
			return false;
			
		}
		
		
		
		return false;
	}


	
	
	

	// Return the list of notifications within a square of side dst (in meters) centered at the position (lat, lng) (it does not matter if the notification is active or not). Do not call Map.getAll().
	public static List<LocNot> getNotsAt(Map<Double, Map<Double, LocNot>> nots, double lat, double lng, double dst) {
		
		
		
		double d=dst/2;
		
	double angle=GPS.angle(d);
	 
	double lat2=lat+angle;
	
	double lat1=lat-angle;	
	
		
	List<Pair < Double ,Map<Double, LocNot > >> LIST1 =nots.getRange(lat1, lat2);
	
	 List<LocNot> The_list= new LinkedList<LocNot>();
	 
	 if(LIST1.empty())
		 return The_list;
	 
	
	double lng2=lng+angle;
	
	double lng1=lng-angle;
	
	
	//Map<Double, LocNot >  the_data= new BST< Double , LocNot>();
	
	
	
	LIST1.findFirst();
	while(!LIST1.last()) {
		
		Map<Double, LocNot >  the_data= LIST1.retrieve().second;
		
		List <Pair <Double, LocNot>>  LIST2=   the_data.getRange(lng1, lng2);
		
		
		if(!LIST2.empty()) {
			LIST2.findFirst();
		while(!LIST2.last()) {
			
		The_list.insert(LIST2.retrieve().second);
		
			LIST2.findNext();
			
		}
		
		The_list.insert(LIST2.retrieve().second);
		
		}
		
		LIST1.findNext();
		
		
	}
	
	
	Map<Double, LocNot >  the_data= LIST1.retrieve().second;
	
	List <Pair <Double, LocNot>>  LIST2=   the_data.getRange(lng1, lng2);
	
	

	if(!LIST2.empty()) {
		LIST2.findFirst();
	while(!LIST2.last()) {
		
	The_list.insert(LIST2.retrieve().second);
	
		LIST2.findNext();
		
	}
	
	
	The_list.insert(LIST2.retrieve().second);
	
	}
		
		return The_list;
	}

	// Return the list of active notifications within a square of side dst (in meters) centered at the position (lat, lng). Do not call Map.getAll().
	public static List<LocNot> getActiveNotsAt(Map<Double, Map<Double, LocNot>> nots, double lat, double lng, double dst) {//DONE
		
		
		
	
		
		double d=dst/2;
		
	double angle=GPS.angle(d);
	 
	double lat2=lat+angle;
	
	double lat1=lat-angle;	
	
	
	List<Pair <  Double ,Map<Double, LocNot > >> LIST1 =nots.getRange(lat1, lat2);
	
	 List<LocNot> The_list= new LinkedList<LocNot>();
	 
	 if(LIST1.empty())
		 return The_list;
	 
	
	double lng2=lng+angle;
	
	double lng1=lng-angle;
//	System.out.println("YAAAAS323");
	
	//Map<Double, LocNot >  the_data= new BST< Double , LocNot>();
	

	
	if(!LIST1.empty()) {
	//	System.out.println("YAAAAS330");
		LIST1.findFirst();
		
	while(!LIST1.last()) {
		
		//System.out.println("YAAAAS333");
		Map<Double, LocNot >  the_data= LIST1.retrieve().second;
		
		List <Pair <Double, LocNot>>  LIST2=   the_data.getRange(lng1, lng2);
		
		if(!LIST2.empty()) {
		LIST2.findFirst();
		
		while(!LIST2.last()) {
			
			if(LIST2.retrieve().second.isActive())
		The_list.insert(LIST2.retrieve().second);
		
			LIST2.findNext();
			
		}
		
		
		if(LIST2.retrieve().second.isActive())
		The_list.insert(LIST2.retrieve().second);
		
	
		
		
		}
		LIST1.findNext();
	}
	
	
	Map<Double, LocNot > the_data= LIST1.retrieve().second;
	
	List <Pair <Double, LocNot>>  LIST2=   the_data.getRange(lng1, lng2);
	
	if(!LIST2.empty()) {
	LIST2.findFirst();
	while(!LIST2.last()) {
		
		if(LIST2.retrieve().second.isActive())
	The_list.insert(LIST2.retrieve().second);
	
		LIST2.findNext();
		
	}
	
	if(LIST2.retrieve().second.isActive())
	The_list.insert(LIST2.retrieve().second);
	
	}
	}
	
		
		return The_list;
	}
	
	
	// Perform task of any active notification within a square of side dst (in meters) centered at the position (lat, lng) (call method perform). Do not call Map.getAll().
	public static void perform(Map<Double, Map<Double, LocNot>> nots, double lat, double lng, double dst) {//DONE
		
		
		double d=dst/2;
		
	double angle=GPS.angle(d);
	 
	double lat2=lat+angle;
	
	double lat1=lat-angle;	
	
		
	List<Pair <  Double ,Map<Double, LocNot > >> LIST1 =nots.getRange(lat1, lat2);
	

	
	 List<LocNot> The_list= new LinkedList<LocNot>();
	 
	 if(LIST1.empty())
		 return ;
	 
	 
	
	double lng2=lng+angle;
	
	double lng1=lng-angle;
//	System.out.println("YAAAAS323");
	
	//Map<Double, LocNot >  the_data= new BST< Double , LocNot>();
	

	
	if(!LIST1.empty()) {
	//	System.out.println("YAAAAS330");
		LIST1.findFirst();
		
	while(!LIST1.last()) {
		
		//System.out.println("YAAAAS333");
		Map<Double, LocNot >  the_data= LIST1.retrieve().second;
		
		List <Pair <Double, LocNot>>  LIST2=   the_data.getRange(lng1, lng2);
		
		if(!LIST2.empty()) {
		LIST2.findFirst();
		
		while(!LIST2.last()) {
			
			if(LIST2.retrieve().second.isActive())
				LIST2.retrieve().second	.perform();
		
			LIST2.findNext();
			
		}
		
		
		if(LIST2.retrieve().second.isActive())
		LIST2.retrieve().second.perform();
		
	
		
		
		}
		LIST1.findNext();
	}
	
	
	Map<Double, LocNot > the_data= LIST1.retrieve().second;
	
	List <Pair <Double, LocNot>>  LIST2=   the_data.getRange(lng1, lng2);
	
	if(!LIST2.empty()) {
		
		LIST2.findFirst();
	
	while(!LIST2.last()) {
		
		if(LIST2.retrieve().second.isActive())
	LIST2.retrieve().second.perform();
	
		LIST2.findNext();
		
	}
	
	
	if(LIST2.retrieve().second.isActive())
	LIST2.retrieve().second.perform();
	}
	
	}
	
		
		
		
		
		
		
		
		
		
		
		
		
	}

	// Return a map that maps every word to the list of notifications in which it appears. The list must have no duplicates.
	public static Map<String, List<LocNot>> index(Map<Double, Map<Double, LocNot>> nots) {//DONE
		
		Map<String, List<LocNot>> the_index_result=new  BST<String, List<LocNot>>();
		
			if(nots.empty())
				
				return the_index_result;
						
		
		
		List <LocNot> the_Notifications= getAllNots( nots);
		
		
		if(the_Notifications.empty())
			return the_index_result;
		
		
			
		
		
			
		the_Notifications.findFirst();
		
		
		while(!the_Notifications.last()) {
			
			String str=the_Notifications.retrieve().getText();
			
			String[] str_arr=str.split(" ");
			
			for(int i=0;i<str_arr.length;i++) {
				
				if(the_index_result.find(str_arr[i])) {
					
					the_index_result.retrieve().insert(the_Notifications.retrieve());
				}
				else {
					List<LocNot>  the_new_notifications=new LinkedList<LocNot> ();
					
					the_new_notifications.insert(the_Notifications.retrieve());
					
					the_index_result.insert(str_arr[i], the_new_notifications);
				}
			}
			the_Notifications.findNext();
			
		}
		String str=the_Notifications.retrieve().getText();
		
		String[] str_arr=str.split(" ");
		
		for(int i=0;i<str_arr.length;i++) {
			
			if(the_index_result.find(str_arr[i])) {
				
				the_index_result.retrieve().insert(the_Notifications.retrieve());
			}
			
			else {
				
				List<LocNot> the_new_notifications=new LinkedList<LocNot> ();
				the_new_notifications.insert(the_Notifications.retrieve());
				the_index_result.insert(str_arr[i], the_new_notifications);
			}
			
			
		}
	
			return the_index_result;
			
			
		
	}
		
		
		
		
		
		
	/*

	// Delete all notifications containing the word w.
	public static void delNots(Map<Double, Map<Double, LocNot>> nots, String w) {
		
		
		 Map<String, List<LocNot>> the_index = index(nots);
		 
		
		 if(the_index.find(w)) {
			 
		//	System.out.println("in the delet method");
			//List<LocNot> the_deleted_list = the_index.retrieve();
			 
			// delNot(nots,the_index.retrieve().retrieve().getLat(), the_index.retrieve().retrieve().getLng());
			 
			// the_index.remove(w);
			 
			 the_index.retrieve().findFirst();
			 
			 while(!the_index.retrieve().last()) {
				 
				 
			// nots.retrieve().remove(the_index.retrieve().retrieve().getLng());
				 
			 delNot(nots,the_index.retrieve().retrieve().getLat(), the_index.retrieve().retrieve().getLng());
				 
			//	 if(nots.retrieve().empty()) {
					 
			//		 nots.remove(the_index.retrieve().retrieve().getLat());	 
				
			//	 }
				 
				 the_index.retrieve().findNext();
				 
			// nots.remove(the_index.retrieve().retrieve().getLat());
			// the_index.retrieve().findNext();
			 }
			 delNot(nots,the_index.retrieve().retrieve().getLat(), the_index.retrieve().retrieve().getLng());
			/*
			 nots.retrieve().remove(the_index.retrieve().retrieve().getLng());
			 
			 if(nots.retrieve().empty()) {
				 nots.remove(the_index.retrieve().retrieve().getLat());
				} 
			*/
			 
			 
		/*	// nots.remove(the_index.retrieve().retrieve().getLat());
		 }
		 
		 else {return;
		 
		 }
		
	}
*/
	
	public static void delNots(Map<Double, Map<Double, LocNot>> nots, String w) {
		   Map <String  , List<LocNot>> map = index(nots);
			if (map.find(w))
			{List<LocNot> ls = map.retrieve();
			if (ls.empty()) return;
			ls.findFirst();
			while (!ls.last()) {
				delNot(nots, ls.retrieve().getLat() , ls.retrieve().getLng());
			ls.findNext();}
				
			delNot(nots, ls.retrieve().getLat() , ls.retrieve().getLng());
			}
		}
	
	
	// Print a list of notifications in the same format used in file.
	public static void print(List<LocNot> l) {
		System.out.println("-------------------------------------------------------------------------------------");
		if (!l.empty()) {
			l.findFirst();
			while (!l.last()) {
				System.out.println(l.retrieve());
				l.findNext();
			}
			System.out.println(l.retrieve());
		} else {
			System.out.println("Empty");
		}
		System.out.println("------------------");
	}

	// Print an index.
	public static void print(Map<String, List<LocNot>> ind) {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		List<Pair<String, List<LocNot>>> l = ind.getAll();
		
		if (!l.empty()) {
			l.findFirst();
			while (!l.last()) {
				System.out.println(l.retrieve().first);
				print(l.retrieve().second);
				l.findNext();
			}
			System.out.println(l.retrieve().first);
			print(l.retrieve().second);
		} else {
			System.out.println("Empty");
		}
		System.out.println("++++++++++++++++++");
	}

}