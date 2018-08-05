package org.wuwer.playground;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static org.wuwer.playground.PreconditionsUtil.nonNullArg;

public class ValidityDTO {

    private final String firstName;
    private final String lastName;
    private final DateTime validityStart;
    private final DateTime validityEnd;

    private ValidityDTO(Builder builder) {
        checkNotNull(builder.validityStart);
        checkNotNull(builder.validityEnd);
        checkState(builder.validityStart.isBefore(builder.validityEnd));

        this.firstName = nonNullArg(builder.firstName);
        this.lastName = nonNullArg(builder.lastName);
        this.validityStart = builder.validityStart;
        this.validityEnd = builder.validityEnd;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public DateTime getValidityStart() {
        return validityStart;
    }

    public DateTime getValidityEnd() {
        return validityEnd;
    }


    public static final class Builder {
        private String firstName;
        private String lastName;
        private DateTime validityStart;
        private DateTime validityEnd;

        private Builder() {
        }

        public static Builder aValidityDTO() {
            return new Builder();
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withValidityStart(DateTime validityStart) {
            this.validityStart = validityStart;
            return this;
        }

        public Builder withValidityEnd(DateTime validityEnd) {
            this.validityEnd = validityEnd;
            return this;
        }

        public ValidityDTO build() {
            return new ValidityDTO(this);
        }

    }
}
