package HotelClasses;

public class Month {
    private Day month[];

    public Month(int numOfDays) {
        month = new Day[numOfDays];
        for (int i = 0; i < numOfDays; i++) {
            this.month[i] = new Day();
        }
    }

    public Day[] getMonth() {
        return this.month;
    }

    public void setAvailability(Reservation reservation) {
        int checkInDay = reservation.getCheckInDate().getDay();
        int checkOutDay = reservation.getCheckOutDate().getDay();
        for (int i = checkInDay - 1; i < checkOutDay; i++) {
            if (i == checkInDay - 1) {
                this.month[i].setIsBooked(true);
                this.month[i].setIsCheckIn(true);
                this.month[i].setReservation(reservation);
            } else if (i == checkOutDay - 1) {
                this.month[i].setIsBooked(true);
                this.month[i].setIsCheckOut(true);
                this.month[i].setReservation(reservation);
            } else {
                this.month[i].setIsBooked(true);
                this.month[i].setReservation(reservation);
            }
        }
    }

    public void resetAvailability(Reservation reservation) {
        int checkInDay = reservation.getCheckInDate().getDay();
        int checkOutDay = reservation.getCheckOutDate().getDay();

        for (int i = checkInDay - 1; i < checkOutDay; i++) {
            if (i == checkInDay - 1) {
                this.month[i].setIsBooked(false);
                this.month[i].setIsCheckIn(false);
                this.month[i].setReservation(null);
            } else if (i == checkOutDay - 1) {
                this.month[i].setIsBooked(false);
                this.month[i].setIsCheckOut(false);
                this.month[i].setReservation(null);
            } else {
                this.month[i].setIsBooked(false);
                this.month[i].setReservation(null);
            }
        }
    }

    public void displayMonth() {
        for (int i = 0; i < month.length; i++) {
            if (i % 10 == 0 && i != 0) {
                System.out.print("|\n");
            }
            if (month[i].getIsBooked()) {
                if (i < 10) {
                    System.out.print("| " + (i + 1) + " - ");
                } else {
                    System.out.print("|" + (i + 1) + " - ");
                }

            } else {
                if (i < 10) {
                    System.out.print("| " + (i + 1) + " + ");
                } else {
                    System.out.print("|" + (i + 1) + " + ");
                }
            }
        }
        System.out.print("|\n");
    }

    // checkin day - 1 so that it follows the array of indeces starting at 0
    public boolean isConflict(Reservation reservation) {
        boolean isConflict = false;
        int checkInDay = reservation.getCheckInDate().getDay() - 1;
        int checkOutDay = reservation.getCheckOutDate().getDay() - 1;
        for (int i = checkInDay; i <= checkOutDay && !isConflict; i++) {
            if (this.month[i].getIsCheckIn() && i == checkOutDay) {
                if (this.month[i].getReservation().getCheckInDate().getHour() <= reservation.getCheckOutDate()
                        .getHour()) {
                    isConflict = true;
                }
            } else if (this.month[i].getIsCheckOut() && i == checkInDay) {
                if (this.month[i].getReservation().getCheckOutDate().getHour() >= reservation.getCheckInDate()
                        .getHour()) {
                    isConflict = true;
                }

            } else if (this.month[i].getIsBooked()) {
                isConflict = true;
            }
        }

        return isConflict;
    }

}