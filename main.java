public class Room {
private int roomNumber; 
private int daysRented; 
private String roomType; 
private String occupantName;

public Room(int roomNumber, String roomType){
   this.roomNumber = roomNumber;
   if(roomType.equalsIgnoreCase("single king")){this.roomType=roomType;}
   else if(roomType.equalsIgnoreCase("suite")){this.roomType = roomType;}
   else{this.roomType = "double queen";}
   daysRented = 0;
   occupantName = null;
}


public int getRoomNumber(){
   return roomNumber;
}

public String getRoomType(){
   return roomType;
}

public int getDaysRented(){
   return daysRented;
}

public String getOccupantName(){
   return occupantName;
}

public boolean setOccupant(String guestName, int daysToStay){
   if (getDaysRented()==0){
       occupantName = guestName;
       daysRented = daysToStay;
       return true;
   }
   return false;
}

public void advanceDay(){
   if (getDaysRented()>0){
       daysRented = daysRented -1;
   }
   else{
       daysRented = 0;
       occupantName = null;
   }
}

public String toString(){
   String condition;
   if (getDaysRented()==0 && getOccupantName()==null){
       condition = "free";
   }
   else {condition = "rented";}
   return ("Room "+ getRoomNumber()+ ": "+ getRoomType()+ " - "+ condition);
}
}

public class Hotel {
    public String hotelName;
public Room[] rooms;
public int totalRooms;


public Hotel(String hotelname,int totalRooms){
      this.totalRooms=totalRooms;
      this.hotelName=hotelname;
      this.rooms=new Room[totalRooms];
      int roomsOnFloor=totalRooms/3;
      int floor=1;int num=1;int count=0;
      String roomType;
      while(count<rooms.length){
          while(num<=roomsOnFloor){
              int roomNum=floor*10+num;
              if(roomNum>30){break;}
              if(roomsOnFloor-num==0){roomType="suite";}
              else if((roomsOnFloor-num)<6&&(roomsOnFloor-num)>=1){roomType="Single king";}
              else{roomType="double queen";}
              rooms[count]=new Room(roomNum,roomType);
              num++;
              count++;
          }
          floor++;
          num=1;
      }
      
    }
    
public int getNumberOccupied(){
   int i = 0;
   int count= 0;
   while (i< rooms.length){
       if (rooms[i].getDaysRented()>0){count++;}
       i++;
   }
   return count;
}

public double getOccupancyRate(){
   double numerator = getNumberOccupied()*1.0;
   double denomitator = totalRooms*1.0;
   return Math.round((numerator/denomitator)*100.0)/100.0;
}

public int getTotalRooms(){
   return totalRooms;
}

public boolean rentRoom(String roomType, String renterName, int numDays){
   boolean r = false;
   int i = 0;
   while (i < rooms.length)
       if (rooms[i].getRoomType().equalsIgnoreCase(roomType) & rooms[i].getDaysRented()==0){
           rooms[i].setOccupant(renterName, numDays);
           r = true;
           break;  
          
       }
       else{i++;}
   return r;
}

public void advanceDay(){
   int i = 0;
   if (getNumberOccupied() > 0){
       while (i<rooms.length){
           if (rooms[i].getDaysRented()>0){rooms[i].advanceDay(); i++;}
           else{i++;}
       }
}
}

public String toString(){
   return (hotelName + ": "+ Math.round(getOccupancyRate()*100) + "%" + " occupied");
}
}

public class Main {
    public static void main(String[] args) {
        
        Room rom=new Room(22,"suite");
        rom.setOccupant("Marya",2);
        rom.advanceDay();
        
        System.out.println(rom.toString());
        
        Hotel h=new Hotel("THE MAKERS’S HOTEL",30);
   
        
        h.rentRoom("suite","Maryam",3);
        h.advanceDay();
        System.out.println(h.toString());
           
    }

}
