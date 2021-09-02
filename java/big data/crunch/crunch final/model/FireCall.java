/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package model;

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class FireCall extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7105820844946701948L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"FireCall\",\"namespace\":\"model\",\"fields\":[{\"name\":\"priority\",\"type\":\"int\",\"doc\":\"The call priority\"},{\"name\":\"call_type\",\"type\":\"string\",\"doc\":\"The type of fire call\"},{\"name\":\"jurisdiction\",\"type\":\"string\",\"doc\":\"The jurisdiction of the agency responsible for responding to the call\"},{\"name\":\"station\",\"type\":\"string\",\"doc\":\"The station that responded to the fire call\"},{\"name\":\"receive_time\",\"type\":\"long\",\"doc\":\"The time the call was received\"},{\"name\":\"dispatch_time\",\"type\":\"long\",\"doc\":\"The time since epoch the call was dispatched\"},{\"name\":\"arrival_time\",\"type\":\"long\",\"doc\":\"The time since epoch of the firefighter arrival\"},{\"name\":\"clear_time\",\"type\":\"long\",\"doc\":\"The time since epoch that the firefighters left\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  /** The call priority */
  @Deprecated public int priority;
  /** The type of fire call */
  @Deprecated public java.lang.CharSequence call_type;
  /** The jurisdiction of the agency responsible for responding to the call */
  @Deprecated public java.lang.CharSequence jurisdiction;
  /** The station that responded to the fire call */
  @Deprecated public java.lang.CharSequence station;
  /** The time the call was received */
  @Deprecated public long receive_time;
  /** The time since epoch the call was dispatched */
  @Deprecated public long dispatch_time;
  /** The time since epoch of the firefighter arrival */
  @Deprecated public long arrival_time;
  /** The time since epoch that the firefighters left */
  @Deprecated public long clear_time;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public FireCall() {}

  /**
   * All-args constructor.
   * @param priority The call priority
   * @param call_type The type of fire call
   * @param jurisdiction The jurisdiction of the agency responsible for responding to the call
   * @param station The station that responded to the fire call
   * @param receive_time The time the call was received
   * @param dispatch_time The time since epoch the call was dispatched
   * @param arrival_time The time since epoch of the firefighter arrival
   * @param clear_time The time since epoch that the firefighters left
   */
  public FireCall(java.lang.Integer priority, java.lang.CharSequence call_type, java.lang.CharSequence jurisdiction, java.lang.CharSequence station, java.lang.Long receive_time, java.lang.Long dispatch_time, java.lang.Long arrival_time, java.lang.Long clear_time) {
    this.priority = priority;
    this.call_type = call_type;
    this.jurisdiction = jurisdiction;
    this.station = station;
    this.receive_time = receive_time;
    this.dispatch_time = dispatch_time;
    this.arrival_time = arrival_time;
    this.clear_time = clear_time;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return priority;
    case 1: return call_type;
    case 2: return jurisdiction;
    case 3: return station;
    case 4: return receive_time;
    case 5: return dispatch_time;
    case 6: return arrival_time;
    case 7: return clear_time;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: priority = (java.lang.Integer)value$; break;
    case 1: call_type = (java.lang.CharSequence)value$; break;
    case 2: jurisdiction = (java.lang.CharSequence)value$; break;
    case 3: station = (java.lang.CharSequence)value$; break;
    case 4: receive_time = (java.lang.Long)value$; break;
    case 5: dispatch_time = (java.lang.Long)value$; break;
    case 6: arrival_time = (java.lang.Long)value$; break;
    case 7: clear_time = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'priority' field.
   * @return The call priority
   */
  public java.lang.Integer getPriority() {
    return priority;
  }

  /**
   * Sets the value of the 'priority' field.
   * The call priority
   * @param value the value to set.
   */
  public void setPriority(java.lang.Integer value) {
    this.priority = value;
  }

  /**
   * Gets the value of the 'call_type' field.
   * @return The type of fire call
   */
  public java.lang.CharSequence getCallType() {
    return call_type;
  }

  /**
   * Sets the value of the 'call_type' field.
   * The type of fire call
   * @param value the value to set.
   */
  public void setCallType(java.lang.CharSequence value) {
    this.call_type = value;
  }

  /**
   * Gets the value of the 'jurisdiction' field.
   * @return The jurisdiction of the agency responsible for responding to the call
   */
  public java.lang.CharSequence getJurisdiction() {
    return jurisdiction;
  }

  /**
   * Sets the value of the 'jurisdiction' field.
   * The jurisdiction of the agency responsible for responding to the call
   * @param value the value to set.
   */
  public void setJurisdiction(java.lang.CharSequence value) {
    this.jurisdiction = value;
  }

  /**
   * Gets the value of the 'station' field.
   * @return The station that responded to the fire call
   */
  public java.lang.CharSequence getStation() {
    return station;
  }

  /**
   * Sets the value of the 'station' field.
   * The station that responded to the fire call
   * @param value the value to set.
   */
  public void setStation(java.lang.CharSequence value) {
    this.station = value;
  }

  /**
   * Gets the value of the 'receive_time' field.
   * @return The time the call was received
   */
  public java.lang.Long getReceiveTime() {
    return receive_time;
  }

  /**
   * Sets the value of the 'receive_time' field.
   * The time the call was received
   * @param value the value to set.
   */
  public void setReceiveTime(java.lang.Long value) {
    this.receive_time = value;
  }

  /**
   * Gets the value of the 'dispatch_time' field.
   * @return The time since epoch the call was dispatched
   */
  public java.lang.Long getDispatchTime() {
    return dispatch_time;
  }

  /**
   * Sets the value of the 'dispatch_time' field.
   * The time since epoch the call was dispatched
   * @param value the value to set.
   */
  public void setDispatchTime(java.lang.Long value) {
    this.dispatch_time = value;
  }

  /**
   * Gets the value of the 'arrival_time' field.
   * @return The time since epoch of the firefighter arrival
   */
  public java.lang.Long getArrivalTime() {
    return arrival_time;
  }

  /**
   * Sets the value of the 'arrival_time' field.
   * The time since epoch of the firefighter arrival
   * @param value the value to set.
   */
  public void setArrivalTime(java.lang.Long value) {
    this.arrival_time = value;
  }

  /**
   * Gets the value of the 'clear_time' field.
   * @return The time since epoch that the firefighters left
   */
  public java.lang.Long getClearTime() {
    return clear_time;
  }

  /**
   * Sets the value of the 'clear_time' field.
   * The time since epoch that the firefighters left
   * @param value the value to set.
   */
  public void setClearTime(java.lang.Long value) {
    this.clear_time = value;
  }

  /**
   * Creates a new FireCall RecordBuilder.
   * @return A new FireCall RecordBuilder
   */
  public static model.FireCall.Builder newBuilder() {
    return new model.FireCall.Builder();
  }

  /**
   * Creates a new FireCall RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new FireCall RecordBuilder
   */
  public static model.FireCall.Builder newBuilder(model.FireCall.Builder other) {
    return new model.FireCall.Builder(other);
  }

  /**
   * Creates a new FireCall RecordBuilder by copying an existing FireCall instance.
   * @param other The existing instance to copy.
   * @return A new FireCall RecordBuilder
   */
  public static model.FireCall.Builder newBuilder(model.FireCall other) {
    return new model.FireCall.Builder(other);
  }

  /**
   * RecordBuilder for FireCall instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<FireCall>
    implements org.apache.avro.data.RecordBuilder<FireCall> {

    /** The call priority */
    private int priority;
    /** The type of fire call */
    private java.lang.CharSequence call_type;
    /** The jurisdiction of the agency responsible for responding to the call */
    private java.lang.CharSequence jurisdiction;
    /** The station that responded to the fire call */
    private java.lang.CharSequence station;
    /** The time the call was received */
    private long receive_time;
    /** The time since epoch the call was dispatched */
    private long dispatch_time;
    /** The time since epoch of the firefighter arrival */
    private long arrival_time;
    /** The time since epoch that the firefighters left */
    private long clear_time;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(model.FireCall.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.priority)) {
        this.priority = data().deepCopy(fields()[0].schema(), other.priority);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.call_type)) {
        this.call_type = data().deepCopy(fields()[1].schema(), other.call_type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.jurisdiction)) {
        this.jurisdiction = data().deepCopy(fields()[2].schema(), other.jurisdiction);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.station)) {
        this.station = data().deepCopy(fields()[3].schema(), other.station);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.receive_time)) {
        this.receive_time = data().deepCopy(fields()[4].schema(), other.receive_time);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.dispatch_time)) {
        this.dispatch_time = data().deepCopy(fields()[5].schema(), other.dispatch_time);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.arrival_time)) {
        this.arrival_time = data().deepCopy(fields()[6].schema(), other.arrival_time);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.clear_time)) {
        this.clear_time = data().deepCopy(fields()[7].schema(), other.clear_time);
        fieldSetFlags()[7] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing FireCall instance
     * @param other The existing instance to copy.
     */
    private Builder(model.FireCall other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.priority)) {
        this.priority = data().deepCopy(fields()[0].schema(), other.priority);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.call_type)) {
        this.call_type = data().deepCopy(fields()[1].schema(), other.call_type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.jurisdiction)) {
        this.jurisdiction = data().deepCopy(fields()[2].schema(), other.jurisdiction);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.station)) {
        this.station = data().deepCopy(fields()[3].schema(), other.station);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.receive_time)) {
        this.receive_time = data().deepCopy(fields()[4].schema(), other.receive_time);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.dispatch_time)) {
        this.dispatch_time = data().deepCopy(fields()[5].schema(), other.dispatch_time);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.arrival_time)) {
        this.arrival_time = data().deepCopy(fields()[6].schema(), other.arrival_time);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.clear_time)) {
        this.clear_time = data().deepCopy(fields()[7].schema(), other.clear_time);
        fieldSetFlags()[7] = true;
      }
    }

    /**
      * Gets the value of the 'priority' field.
      * The call priority
      * @return The value.
      */
    public java.lang.Integer getPriority() {
      return priority;
    }

    /**
      * Sets the value of the 'priority' field.
      * The call priority
      * @param value The value of 'priority'.
      * @return This builder.
      */
    public model.FireCall.Builder setPriority(int value) {
      validate(fields()[0], value);
      this.priority = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'priority' field has been set.
      * The call priority
      * @return True if the 'priority' field has been set, false otherwise.
      */
    public boolean hasPriority() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'priority' field.
      * The call priority
      * @return This builder.
      */
    public model.FireCall.Builder clearPriority() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'call_type' field.
      * The type of fire call
      * @return The value.
      */
    public java.lang.CharSequence getCallType() {
      return call_type;
    }

    /**
      * Sets the value of the 'call_type' field.
      * The type of fire call
      * @param value The value of 'call_type'.
      * @return This builder.
      */
    public model.FireCall.Builder setCallType(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.call_type = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'call_type' field has been set.
      * The type of fire call
      * @return True if the 'call_type' field has been set, false otherwise.
      */
    public boolean hasCallType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'call_type' field.
      * The type of fire call
      * @return This builder.
      */
    public model.FireCall.Builder clearCallType() {
      call_type = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'jurisdiction' field.
      * The jurisdiction of the agency responsible for responding to the call
      * @return The value.
      */
    public java.lang.CharSequence getJurisdiction() {
      return jurisdiction;
    }

    /**
      * Sets the value of the 'jurisdiction' field.
      * The jurisdiction of the agency responsible for responding to the call
      * @param value The value of 'jurisdiction'.
      * @return This builder.
      */
    public model.FireCall.Builder setJurisdiction(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.jurisdiction = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'jurisdiction' field has been set.
      * The jurisdiction of the agency responsible for responding to the call
      * @return True if the 'jurisdiction' field has been set, false otherwise.
      */
    public boolean hasJurisdiction() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'jurisdiction' field.
      * The jurisdiction of the agency responsible for responding to the call
      * @return This builder.
      */
    public model.FireCall.Builder clearJurisdiction() {
      jurisdiction = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'station' field.
      * The station that responded to the fire call
      * @return The value.
      */
    public java.lang.CharSequence getStation() {
      return station;
    }

    /**
      * Sets the value of the 'station' field.
      * The station that responded to the fire call
      * @param value The value of 'station'.
      * @return This builder.
      */
    public model.FireCall.Builder setStation(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.station = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'station' field has been set.
      * The station that responded to the fire call
      * @return True if the 'station' field has been set, false otherwise.
      */
    public boolean hasStation() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'station' field.
      * The station that responded to the fire call
      * @return This builder.
      */
    public model.FireCall.Builder clearStation() {
      station = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'receive_time' field.
      * The time the call was received
      * @return The value.
      */
    public java.lang.Long getReceiveTime() {
      return receive_time;
    }

    /**
      * Sets the value of the 'receive_time' field.
      * The time the call was received
      * @param value The value of 'receive_time'.
      * @return This builder.
      */
    public model.FireCall.Builder setReceiveTime(long value) {
      validate(fields()[4], value);
      this.receive_time = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'receive_time' field has been set.
      * The time the call was received
      * @return True if the 'receive_time' field has been set, false otherwise.
      */
    public boolean hasReceiveTime() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'receive_time' field.
      * The time the call was received
      * @return This builder.
      */
    public model.FireCall.Builder clearReceiveTime() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'dispatch_time' field.
      * The time since epoch the call was dispatched
      * @return The value.
      */
    public java.lang.Long getDispatchTime() {
      return dispatch_time;
    }

    /**
      * Sets the value of the 'dispatch_time' field.
      * The time since epoch the call was dispatched
      * @param value The value of 'dispatch_time'.
      * @return This builder.
      */
    public model.FireCall.Builder setDispatchTime(long value) {
      validate(fields()[5], value);
      this.dispatch_time = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'dispatch_time' field has been set.
      * The time since epoch the call was dispatched
      * @return True if the 'dispatch_time' field has been set, false otherwise.
      */
    public boolean hasDispatchTime() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'dispatch_time' field.
      * The time since epoch the call was dispatched
      * @return This builder.
      */
    public model.FireCall.Builder clearDispatchTime() {
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'arrival_time' field.
      * The time since epoch of the firefighter arrival
      * @return The value.
      */
    public java.lang.Long getArrivalTime() {
      return arrival_time;
    }

    /**
      * Sets the value of the 'arrival_time' field.
      * The time since epoch of the firefighter arrival
      * @param value The value of 'arrival_time'.
      * @return This builder.
      */
    public model.FireCall.Builder setArrivalTime(long value) {
      validate(fields()[6], value);
      this.arrival_time = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'arrival_time' field has been set.
      * The time since epoch of the firefighter arrival
      * @return True if the 'arrival_time' field has been set, false otherwise.
      */
    public boolean hasArrivalTime() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'arrival_time' field.
      * The time since epoch of the firefighter arrival
      * @return This builder.
      */
    public model.FireCall.Builder clearArrivalTime() {
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'clear_time' field.
      * The time since epoch that the firefighters left
      * @return The value.
      */
    public java.lang.Long getClearTime() {
      return clear_time;
    }

    /**
      * Sets the value of the 'clear_time' field.
      * The time since epoch that the firefighters left
      * @param value The value of 'clear_time'.
      * @return This builder.
      */
    public model.FireCall.Builder setClearTime(long value) {
      validate(fields()[7], value);
      this.clear_time = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'clear_time' field has been set.
      * The time since epoch that the firefighters left
      * @return True if the 'clear_time' field has been set, false otherwise.
      */
    public boolean hasClearTime() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'clear_time' field.
      * The time since epoch that the firefighters left
      * @return This builder.
      */
    public model.FireCall.Builder clearClearTime() {
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    public FireCall build() {
      try {
        FireCall record = new FireCall();
        record.priority = fieldSetFlags()[0] ? this.priority : (java.lang.Integer) defaultValue(fields()[0]);
        record.call_type = fieldSetFlags()[1] ? this.call_type : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.jurisdiction = fieldSetFlags()[2] ? this.jurisdiction : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.station = fieldSetFlags()[3] ? this.station : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.receive_time = fieldSetFlags()[4] ? this.receive_time : (java.lang.Long) defaultValue(fields()[4]);
        record.dispatch_time = fieldSetFlags()[5] ? this.dispatch_time : (java.lang.Long) defaultValue(fields()[5]);
        record.arrival_time = fieldSetFlags()[6] ? this.arrival_time : (java.lang.Long) defaultValue(fields()[6]);
        record.clear_time = fieldSetFlags()[7] ? this.clear_time : (java.lang.Long) defaultValue(fields()[7]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
