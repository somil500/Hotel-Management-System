import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Executor;

public class Hotel_management
{
    public  static  void main(String[] args)
    {
        System.out.println("HOTEL MANAGEMENT ");

        // CONNECT JAVA WITH DATABASE

        final String Url="jdbc:mysql://127.0.0.1:3306/hotal_management";
        final String Username="root";
        final String Password="Somil@123";
        //Driver loaded
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("DRIVER LOADED SUCCESSFULLY");

        } catch (Exception e)
        {

            System.out.println("DRIVER NOT LOADED SUCCESSFULLY "+e.getMessage());
        }
        try
        {
            Connection con= DriverManager.getConnection(Url,Username,Password);
            Statement stt= con.createStatement();
            System.out.println("connection successfully done");


            while (true)

            {
                System.out.println();
                System.out.println("<---------------------> WELCOME TO HOTEL MANAGEMENT SYSTEM<------------------------>");
                Scanner sc=new Scanner(System.in);
                System.out.println();
                System.out.println(" Services !!!");

                System.out.println(" 1------->New Reservation");

              ///  System.out.println(" 2-------->Get  Receipt");

                System.out.println(" 2------->View All Reservation IN Hotel");

                System.out.println(" 3------->View Room Detailed");

                System.out.println(" 4------->Update Reservation detailed");

                System.out.println(" 5------->Close Reservation");

                System.out.println(" 0------->Exist");

                System.out.println();

                System.out.println("Enter The Services!");
                int choice=sc.nextInt();
                 switch (choice)
                 {
                     case 1:
                         System.out.println("<----------------Welcome To  Your First Time Reservation-----------------------> ");
                         Reservation(con,sc,stt);
                         break;
                     case 2:
                         System.out.println("<-------------------------- All Reservation Detail ---------------------------------->");
                         View_reservation(con,sc,stt);
                     break;
                     case 3:
                         System.out.println("<------------------------------- Room Detail---------------------------------------->");
                         View_room(con,sc,stt);
                         break;
                     case 4:
                         System.out.println("<----------------------- Welcome TO Update Reservation Detailed ----------------------> ");
                         Re_reservation(con,sc,stt);
                         break;
                     case 5:
                         System.out.println("<---------------------------------Delete  Reservation--------------------------------------> ");
                         Cancel_Reservation(con,sc,stt);

                         break;
                     case 0:
                         System.out.println("<------------------------------------....Existing....--------------------------------------->");
                         Exist();
                         sc.close();
                         break;
                     default:

                         System.out.println("<-------------------Invalid Selection Please Try With Valid  Selection--------------------->");

                 }



            }

        } catch (SQLException e)
        {
            System.out.println("Server Down ");

            throw new RuntimeException(e);
        }
        catch (Exception e)
        {
            System.out.println(" Server have closed");

            //throw new RuntimeException(e);
        }




    }
    public  static void Reservation(Connection con,Scanner sc,Statement stt)
    {

       try
       {
           System.out.println("Enter guest name * ");

           String guest_name=sc.next();
           sc.nextLine();
           System.out.println();
           System.out.println("Enter room number * ");
           int room_number=sc.nextInt();
           System.out.println();
           System.out.println("Enter contact number * ");
           String contact=sc.next();

           String sql_query="INSERT INTO reservations(guest_name,room_number,contact_number)"+
                   "VALUES('"+guest_name+"',"+room_number+",'"+contact+"')";
           int Row_affected=stt.executeUpdate(sql_query);
           if (Row_affected>0)

           {
               String sql="SELECT * FROM reservations WHERE guest_name = ? AND room_number = ? AND contact_number= ? ";
               System.out.println("Reservation Successfully Completed .... ");
               PreparedStatement pstt=con.prepareStatement(sql);
               pstt.setString(1,guest_name);
               pstt.setInt(2,room_number);
               pstt.setString(3,contact);
               ResultSet rs=pstt.executeQuery();

               while (rs.next())
               {
                   int Reservation_id=rs.getInt("reservation_id");
                   String guestname= rs.getString("guest_name");
                   int room= rs.getInt("room_number");
                   String contact1=rs.getString("contact_number");
                   String date=rs.getString("reservation_date").toString();


                   System.out.println(" <--------------------------- RESERVATION RECEIPT -------------------------------------->");
                   System.out.println("|                                           " +
                           "           |  Date -------------> "+date);
                   System.out.println("|  Guest Name--------> "+guestname);
                   System.out.println("|  Reservation id----> "+Reservation_id);
                   System.out.println("|  Room Number-------> "+room);
                   System.out.println("|  Contact Number----> "+contact1);
                   System.out.println("<----------------------------------------------------------------------------------------->");


               }


           }else
           {
               System.out.println("Reservation Failed.....");
               System.out.println("Something is wrong....");

           }

       } catch (Exception e)
       {
           System.out.println("Error Invalid Detailed At Reservation!!!"+e.getMessage());
           System.out.println("Guest Name Must Be Without Space!!");

           throw new RuntimeException(e);
       }






    }
    public  static void View_reservation(Connection con,Scanner sc,Statement stt)
    {

        try
        {


            String sql_query="SELECT reservation_id,guest_name,room_number,contact_number,reservation_date FROM reservations";
         ResultSet  rs=  stt.executeQuery(sql_query);
           while (rs.next())
           {
               int id=rs.getInt("reservation_id");
               String guest_name= rs.getString("guest_name");
               int room= rs.getInt("room_number");
               String contact=rs.getString("contact_number");
               String date=rs.getString("reservation_date").toString();

            //   System.out.println(" <--------------------------------------------------------------------------------------->");

            //   System.out.println(" | ID |    Guest Name    | Room No |   Contact Number    |   Date |");
               System.out.println(" | ID--> "+id+" | NAME--> "+guest_name+"   | ROOM NO--> "+room+" | CONTACT--> " +contact+" | DATE--> "+date+"|");

               System.out.println("<----------------------------------------------------------------------------------------------------------------->");


           }





        } catch (Exception e)
        {
            System.out.println("something wrong at view reservation");
            throw new RuntimeException(e);
        }




    }
    public static void View_room(Connection con,Scanner sc,Statement stt)
    {
        try
        {
            System.out.println("Enter the guest name....");
            String name=sc.next();
            sc.nextLine();
            System.out.println("Enter Reservation id.....");
            int id=sc.nextInt();
            String sql_query="SELECT room_number FROM reservations WHERE reservation_id="+id;

           ResultSet rs=stt.executeQuery(sql_query);
            if (rs.next())
            {
                int room_num=rs.getInt("room_number");
                System.out.println("<----------------------- Reservation Room Number------------------------------------>");
                System.out.println(" Room no--->"+room_num);
                System.out.println(" Guest name--->"+name);
                System.out.println(" Reservation id---->"+id);
                System.out.println("<----------------------------------------------------------------------------------->");


            }
            else
            {
                System.out.println("Reservation does not exist");
            }



        } catch (Exception e)
        {
            System.out.println("something is wrong at view room  ");
            throw new RuntimeException(e);
        }

    }
    public  static  void Re_reservation(Connection con,Scanner sc,Statement stt)
    {
        try
        {

            System.out.println("Select services.........  ");

            System.out.println(" 1------> "+" Update Guest Name");

            System.out.println(" 2------>" + "Update Contact no ");


            System.out.println(" 3------> "+"Update Room no");



           int option=sc.nextInt();

           switch (option)
           {
               case 1:
                   System.out.println("Enter reservation id");
                   int id=sc.nextInt();
                   System.out.println("Enter new guest name");
                   String name=sc.next();
                   sc.nextLine();
                   String sql_query1="UPDATE reservations SET guest_name='"+name+"' WHERE reservation_id="+id;
                   int Row_affected=stt.executeUpdate(sql_query1);
                   if (Row_affected>0)
                   {
                       System.out.println("Name successfully Updated  for reservation id ---->"+id);
                   }else
                   {
                       System.out.println("Update   failed");

                   }
                 break;
               case 2:
                   System.out.println("Enter reservation id");
                   int id1=sc.nextInt();
                   System.out.println("Enter new contact number");
                   String contact=sc.next();
                   sc.nextLine();
                   String sql_query2="UPDATE reservations SET contact_number='"+contact+"' WHERE reservation_id="+id1 ;
                   int Row_affected1=stt.executeUpdate(sql_query2);
                   if (Row_affected1>0)
                   {
                       System.out.println("Contact number successfully updated for reservation id ----->"+id1);
                   }else
                   {
                       System.out.println("Reservation failed");

                   }
                   break;
               case 3:
                   System.out.println("Enter reservation id");
                   int id2=sc.nextInt();
                   System.out.println("Enter  new room number ");
                   int room=sc.nextInt();
                   String sql_query3="UPDATE reservations SET room_number="+room+"  WHERE reservation_id="+id2 ;
                   int Row_affected2=stt.executeUpdate(sql_query3);
                   if (Row_affected2>0)
                   {
                       System.out.println(" Room number successfully Update  for reservation id-----> "+id2);
                   }else
                   {
                       System.out.println("Reservation failed ");

                   }
                   break;
               default:{
                   System.out.println("Invalid option chose one correct");
               }
           }



        } catch (Exception e)
        {
            System.out.println("some things is wrong at update ");
            throw new RuntimeException(e);
        }
        System.out.println(" ------------------------------------------------------------------------------------------- ");


    }
    public static void Cancel_Reservation(Connection con,Scanner sc,Statement stt)
    {
        try
        {
            System.out.println("Enter guest name");
            String name=sc.next();
            sc.nextLine();
            System.out.println("Enter reservation id");
            int id=sc.nextInt();
            String sql_query="DELETE FROM reservations where reservation_id= "+id;

            if(exist_reservation(con,sc,stt,id))
            {
                int Row_affected1=stt.executeUpdate(sql_query);
                if (Row_affected1>0)
                {
                    System.out.print("Reservation successfully Deleted for id ------>"+id);
                    System.out.println("  and name--------->"+name);
                }else
                {
                    System.out.println("Reservation did not deleted");

                }

            }
            else
            {
                System.out.println("Reservation not found for id------>"+id);

                System.out.println("name------->"+name);


            }





        } catch (Exception e)
        {
            System.out.println("some thing is wrong at cancel reservation ");
            throw new RuntimeException(e);
        }
        System.out.println("--------------------------------------------------------------------------------------------");

    }
   public static boolean exist_reservation(Connection con,Scanner sc,Statement stt,int id)
   {
       try
       {

           String sql_query="SELECT reservation_id FROM reservations WHERE reservation_id= "+id;
         ResultSet  rs=stt.executeQuery(sql_query);

           return rs.next();


       } catch (Exception e)
       {
           System.out.println("something is wrong at exist_reservation");

           throw new RuntimeException(e);
       }




   }
    public static void Exist() throws InterruptedException
    {
        System.out.println("Existing system ");
        int i=5;
        while(i!=0)
        {
            System.out.print("*");
            Thread.sleep(450);
            i--;
        }
        System.out.println();
        System.out.println("           !!!!!!!!!!Thank You For Using Hotel Management System!!!!!!!       ");


    }

}
