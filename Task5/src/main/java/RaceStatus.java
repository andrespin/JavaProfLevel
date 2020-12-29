

public class RaceStatus {

    private boolean isWinner;
    private boolean isPreparationBegun;
    private boolean isRaceStarted;
    private boolean isRaceEnded;

    public synchronized void printWinner(Car car){
        if(!isWinner){
            System.out.println(car.getName() + " - Победитель!!!");
            isWinner = true;
        }
    }

    public synchronized void printAboutRacePreparation(){
        if(!isPreparationBegun){
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
            isPreparationBegun = true;
        }
    }

    public synchronized void printAboutRaceStart(){
        if(!isRaceStarted){
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            isRaceStarted = true;
        }
    }

    public synchronized void printAboutRaceEnd(){
        if(!isRaceEnded){
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
            isRaceEnded = true;
        }
    }

}
