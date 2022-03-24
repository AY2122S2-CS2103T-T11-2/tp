package seedu.address.model.client;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Appointment {
    public static final String MESSAGE_CONSTRAINTS =
            "Appointment should be in the form of"
                    + " 'yyyy-MM-dd-HH-hh'.\nIt can't be empty when using appointment command";

    public static final String EMPTY_APPOINTMENT = "";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
    public final String value;
    public final LocalDateTime appointmentTime;


    /**
     * Takes in a string of time of appointment, check for whether it is valid
     * and initializes the value variable.
     * @param time string of time of appointment
     */
    public Appointment(String time) {
        requireNonNull(time);
        checkArgument(isValidAppointment(time), MESSAGE_CONSTRAINTS);
        if (time == "") {
            appointmentTime = null;
        } else {
            appointmentTime = LocalDateTime.parse(time, FORMATTER);
        }
        value = time;
    }

    /**
     * Checks whether the input String is of the correct format.
     */
    public static boolean isValidAppointment(String time) {
        if (time == EMPTY_APPOINTMENT) {
            return true;
        }
        try {
            LocalDateTime parsedTime = LocalDateTime.parse(time, FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns an easy-to-read String of the appointment time
     */
    public String getAppointmentDetail() {
        if (appointmentTime == null) {
            return "No appointment with the person";
        } else {
            String pattern = "hh:mm a";
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(pattern);

            return "Appointment on " + appointmentTime.format(timeFormatter.withLocale(Locale.ENGLISH)) + " "
                    + appointmentTime.getMonth() + " " + appointmentTime.getDayOfMonth() + " "
                    + appointmentTime.getYear();
        }
    }

    /**
     * Returns the appointmentTime object, of the type LocalDateTime, it could be possibly be null.
     * @return
     */
    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                && value.equals(((Appointment) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
