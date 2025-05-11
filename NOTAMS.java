public class NOTAMS {
    public static boolean messages=false;
    public static void NOTAMSmenu(){
        System.out.println("~~~Messages~~~");
        if(messages){
            for(int i=0; i<RandomEmergency.emergencies.size(); i++){
                if(RandomEmergency.emergencies.get(i).ifCrash()){
                    System.out.println("Good afternoon. This email comes from the National Transportation Safety Board (NTSB).\nWe are sending this email to inform you that one of your recent flight "+RandomEmergency.emergencies.get(i).getFlight().getNumber()+" has crashed during its voyage.");
                    System.out.println("We are attempting investigation efforts of the crash and have concluded that the cause of this disaster was ultimately due to "+RandomEmergency.emergencies.get(i).getIssue()+". We regret to inform you that all "+Demand.getPassengers(RandomEmergency.emergencies.get(i).getFlight())+" souls were lost.");
                    try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                }
                else if(RandomEmergency.emergencies.get(i).ifEmerLand()){
                    System.out.println("COMPANY TEXT FROM: "+ RandomEmergency.emergencies.get(i).getFlight().getNumber());
                    System.out.println("SUCCESSFUL EMERGENCY LANDING EXECUTED");
                    String issue = RandomEmergency.emergencies.get(i).getIssue();
                    String response = issue.trim().toUpperCase();
                    System.out.println("CAUSE: "+response);
                    System.out.println("EVACUATION EFFORTS: $"+Math.round(RandomEmergency.lostmoney*100.0)/100.0);
                    try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                }
            }
        }
        messages = false;
            
        System.out.println("~~~Trend in the economy~~~");
        if(Database.prices.size()!=0){
            double predict=0.0;
            if(Database.price-88.3>=0){
                if(Math.random()<((double)((Database.price-88.3)/20.0)+0.5)){
                    predict = Database.price-Math.random()*(1.1479);
                }
                else{
                    predict = Database.price+Math.random()*(1.1479);
                }
            }
            if(Database.price-88.3<0){
                if(Math.random()<((double)((88.3-Database.price)/20.0)+0.5)){
                    predict = Database.price+Math.random()*(1.1479);
                }
                else{
                    predict = Database.price-Math.random()*(1.1479);

                }
            }
            System.out.println("The average trend of the price for Jet A-1 fuel is ");
            if(Database.prices.get(Database.prices.size()-1)<Database.prices.get(0)){
                System.out.print("going down. It is recommended to wait until prices rise again if you are investing. ");
                System.out.println("It is predicted that the price for the next hour will be $"+Math.round(predict*100.0)/100.0);
            }
            else if(Database.prices.get(Database.prices.size()-1)>Database.prices.get(0)){
                System.out.print("going up. If you invested in jet fuel, we recommend you to sell your fuel before the prices crash. ");
                System.out.println("It is predicted that the price for the next hour will be $"+Math.round(predict*100.0)/100.0);
            }
            else{
                System.out.print("not changing. ");
                if(Math.random()>0.5){
                    System.out.println("It is predicted that the price for the next hour will be $"+Math.round(predict*100.0)/100.0);
                }
                else{System.out.println("It is predicted that the price for the next hour will be $"+Math.round(predict*100.0)/100.0);
                }
            }
        }
        else{
            System.out.println("To view the trend, see prices first in the economy tab.");
        }
        try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println("~~~News & Changelog~~~");
        System.out.println("V1.5.2 - Added a lot");
        System.out.println("- New economy\n- Aircraft can now be purchased and the aircraft tab is open\n- Added fuel prices and a stabilizing method\n- Added 787\n- Fully implemented failure and events system\n- Aircraft availability added, prevents user from creating infinite flights\n-Implemented NOTAMs and messages\n- Added news& changelog\n- Planes are now lost when you crash");
        try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
