
public class Money {

    private final int euros;
    private final int cents;

    public Money(int euros, int cents) {

        if (cents > 99) {
            euros = euros + cents / 100;
            cents = cents % 100;
        }

        this.euros = euros;
        this.cents = cents;
    }

    public int euros() {
        return this.euros;
    }

    public int cents() {
        return this.cents;
    }

    public String toString() {
        String zero = "";
        if (this.cents < 10) {
            zero = "0";
        }

        return this.euros + "." + zero + this.cents + "e";
    }
    
    public Money plus(Money addition) {
        Money newMoney = new Money(this.euros + addition.euros(), this.cents + addition.cents());
        
        return newMoney;
    }
    
    public boolean lessThan(Money compared) {
        // Less euros OR (same euros AND less cents).
        return (this.euros < compared.euros() || (this.euros == compared.euros() && this.cents < compared.cents()));
    }

    public Money minus(Money decreaser) {
        // Subtract the decreaser euros from the current euros, and the decreaser cents from the current cents.
        int newEuros = this.euros - decreaser.euros();
        int newCents = this.cents - decreaser.cents();

        // If the difference of current cents and decreaser cents is negative, then decrement euros accordingly, and store remainder in newCents.
        if (newCents < 0) {
            newEuros = newEuros + (newCents / 100) - 1; // ***
            newCents = (newCents % 100) + 100;
        }

        // If the difference of euros is negative, then return 0. Otherwise, return the difference.
        if (newEuros < 0) {
            Money newMoney = new Money(0, 0);
            return newMoney;
        } else {
            Money newMoney = new Money(newEuros, newCents);
            return newMoney;
        }
    }
}
