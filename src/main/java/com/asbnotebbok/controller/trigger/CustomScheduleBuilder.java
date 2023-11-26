/*
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */

package com.asbnotebbok.controller.trigger;

import org.quartz.*;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.quartz.spi.MutableTrigger;

/**
 * <code>customScheduleBuilder</code> is a {@link ScheduleBuilder}
 * that defines strict/literal interval-based schedules for 
 * <code>Trigger</code>s.
 *  
 * <p>Quartz provides a builder-style API for constructing scheduling-related
 * entities via a Domain-Specific Language (DSL).  The DSL can best be
 * utilized through the usage of static imports of the methods on the classes
 * <code>TriggerBuilder</code>, <code>JobBuilder</code>, 
 * <code>DateBuilder</code>, <code>JobKey</code>, <code>TriggerKey</code> 
 * and the various <code>ScheduleBuilder</code> implementations.</p>
 * 
 * <p>Client code can then use the DSL to write code such as this:</p>
 * <pre>
 *         JobDetail job = newJob(MyJob.class)
 *             .withIdentity("myJob")
 *             .build();
 *             
 *         Trigger trigger = newTrigger() 
 *             .withIdentity(triggerKey("myTrigger", "myTriggerGroup"))
 *             .withSchedule(customSchedule()
 *                 .withIntervalInHours(1)
 *                 .repeatForever())
 *             .startAt(futureDate(10, MINUTES))
 *             .build();
 *         
 *         scheduler.scheduleJob(job, trigger);
 * <pre>
 *
 * @see SimpleTrigger
 * @see CalendarIntervalScheduleBuilder
 * @see CronScheduleBuilder
 * @see ScheduleBuilder
 * @see TriggerBuilder
 */
public class CustomScheduleBuilder extends ScheduleBuilder<CustomTrigger> {

    private long interval = 0;
    private int repeatCount = 0;
    private int misfireInstruction = CustomTrigger.MISFIRE_INSTRUCTION_SMART_POLICY;

    protected CustomScheduleBuilder() {
    }
    
    /**
     * Create a customScheduleBuilder.
     * 
     * @return the new customScheduleBuilder
     */
    public static CustomScheduleBuilder customSchedule() {
        return new CustomScheduleBuilder();
    }
    
    /**
     * Create a customScheduleBuilder set to repeat forever with a 1 minute interval.
     * 
     * @return the new customScheduleBuilder
     */
    public static CustomScheduleBuilder repeatMinutelyForever() {

        return customSchedule()
            .withIntervalInMinutes(1)
            .repeatForever();
    }

    /**
     * Create a customScheduleBuilder set to repeat forever with an interval
     * of the given number of minutes.
     * 
     * @return the new customScheduleBuilder
     */
    public static CustomScheduleBuilder repeatMinutelyForever(int minutes) {

        return customSchedule()
            .withIntervalInMinutes(minutes)
            .repeatForever();
    }

    /**
     * Create a customScheduleBuilder set to repeat forever with a 1 second interval.
     * 
     * @return the new customScheduleBuilder
     */
    public static CustomScheduleBuilder repeatSecondlyForever() {

        return customSchedule()
            .withIntervalInSeconds(1)
            .repeatForever();
    }

    /**
     * Create a customScheduleBuilder set to repeat forever with an interval
     * of the given number of seconds.
     * 
     * @return the new customScheduleBuilder
     */
    public static CustomScheduleBuilder repeatSecondlyForever(int seconds) {

        return customSchedule()
            .withIntervalInSeconds(seconds)
            .repeatForever();
    }
    
    /**
     * Create a customScheduleBuilder set to repeat forever with a 1 hour interval.
     * 
     * @return the new customScheduleBuilder
     */
    public static CustomScheduleBuilder repeatHourlyForever() {

        return customSchedule()
            .withIntervalInHours(1)
            .repeatForever();
    }

    /**
     * Create a customScheduleBuilder set to repeat forever with an interval
     * of the given number of hours.
     * 
     * @return the new customScheduleBuilder
     */
    public static CustomScheduleBuilder repeatHourlyForever(int hours) {

        return customSchedule()
            .withIntervalInHours(hours)
            .repeatForever();
    }

    /**
     * Create a customScheduleBuilder set to repeat the given number
     * of times - 1  with a 1 minute interval.
     * 
     * <p>Note: Total count = 1 (at start time) + repeat count</p>
     * 
     * @return the new customScheduleBuilder
     */
    public static CustomScheduleBuilder repeatMinutelyForTotalCount(int count) {
        if(count < 1)
            throw new IllegalArgumentException("Total count of firings must be at least one! Given count: " + count);

        return customSchedule()
            .withIntervalInMinutes(1)
            .withRepeatCount(count - 1);
    }

    /**
     * Create a customScheduleBuilder set to repeat the given number
     * of times - 1  with an interval of the given number of minutes.
     * 
     * <p>Note: Total count = 1 (at start time) + repeat count</p>
     * 
     * @return the new customScheduleBuilder
     */
    public static CustomScheduleBuilder repeatMinutelyForTotalCount(int count, int minutes) {
        if(count < 1)
            throw new IllegalArgumentException("Total count of firings must be at least one! Given count: " + count);

        return customSchedule()
            .withIntervalInMinutes(minutes)
            .withRepeatCount(count - 1);
    }
    
    /**
     * Create a customScheduleBuilder set to repeat the given number
     * of times - 1  with a 1 second interval.
     * 
     * <p>Note: Total count = 1 (at start time) + repeat count</p>
     * 
     * @return the new customScheduleBuilder
     */
    public static CustomScheduleBuilder repeatSecondlyForTotalCount(int count) {
        if(count < 1)
            throw new IllegalArgumentException("Total count of firings must be at least one! Given count: " + count);

        return customSchedule()
            .withIntervalInSeconds(1)
            .withRepeatCount(count - 1);
    }

    /**
     * Create a customScheduleBuilder set to repeat the given number
     * of times - 1  with an interval of the given number of seconds.
     * 
     * <p>Note: Total count = 1 (at start time) + repeat count</p>
     * 
     * @return the new customScheduleBuilder
     */
    public static CustomScheduleBuilder repeatSecondlyForTotalCount(int count, int seconds) {
        if(count < 1)
            throw new IllegalArgumentException("Total count of firings must be at least one! Given count: " + count);

        return customSchedule()
            .withIntervalInSeconds(seconds)
            .withRepeatCount(count - 1);
    }
    
    /**
     * Create a customScheduleBuilder set to repeat the given number
     * of times - 1  with a 1 hour interval.
     * 
     * <p>Note: Total count = 1 (at start time) + repeat count</p>
     * 
     * @return the new customScheduleBuilder
     */
    public static CustomScheduleBuilder repeatHourlyForTotalCount(int count) {
        if(count < 1)
            throw new IllegalArgumentException("Total count of firings must be at least one! Given count: " + count);

        return customSchedule()
            .withIntervalInHours(1)
            .withRepeatCount(count - 1);
    }

    /**
     * Create a customScheduleBuilder set to repeat the given number
     * of times - 1  with an interval of the given number of hours.
     * 
     * <p>Note: Total count = 1 (at start time) + repeat count</p>
     * 
     * @return the new customScheduleBuilder
     */
    public static CustomScheduleBuilder repeatHourlyForTotalCount(int count, int hours) {
        if(count < 1)
            throw new IllegalArgumentException("Total count of firings must be at least one! Given count: " + count);

        return customSchedule()
            .withIntervalInHours(hours)
            .withRepeatCount(count - 1);
    }
    
    /**
     * Build the actual Trigger -- NOT intended to be invoked by end users,
     * but will rather be invoked by a TriggerBuilder which this 
     * ScheduleBuilder is given to.
     * 
     * @see TriggerBuilder#withSchedule(ScheduleBuilder)
     */
    @Override
    public MutableTrigger build() {

        CustomTriggerImpl st = new CustomTriggerImpl();
        st.setRepeatInterval(interval);
        st.setRepeatCount(repeatCount);
        st.setMisfireInstruction(misfireInstruction);
        
        return st;
    }

    /**
     * Specify a repeat interval in milliseconds. 
     * 
     * @param intervalInMillis the number of seconds at which the trigger should repeat.
     * @return the updated customScheduleBuilder
     * @see SimpleTrigger#getRepeatInterval()
     * @see #withRepeatCount(int)
     */
    public CustomScheduleBuilder withIntervalInMilliseconds(long intervalInMillis) {
        this.interval = intervalInMillis;
        return this;
    }
    
    /**
     * Specify a repeat interval in seconds - which will then be multiplied
     * by 1000 to produce milliseconds. 
     * 
     * @param intervalInSeconds the number of seconds at which the trigger should repeat.
     * @return the updated customScheduleBuilder
     * @see SimpleTrigger#getRepeatInterval()
     * @see #withRepeatCount(int)
     */
    public CustomScheduleBuilder withIntervalInSeconds(int intervalInSeconds) {
        this.interval = intervalInSeconds * 1000L;
        return this;
    }
    
    /**
     * Specify a repeat interval in minutes - which will then be multiplied
     * by 60 * 1000 to produce milliseconds. 
     * 
     * @param intervalInMinutes the number of seconds at which the trigger should repeat.
     * @return the updated customScheduleBuilder
     * @see SimpleTrigger#getRepeatInterval()
     * @see #withRepeatCount(int)
     */
    public CustomScheduleBuilder withIntervalInMinutes(int intervalInMinutes) {
        this.interval = intervalInMinutes * DateBuilder.MILLISECONDS_IN_MINUTE;
        return this;
    }

    /**
     * Specify a repeat interval in minutes - which will then be multiplied
     * by 60 * 60 * 1000 to produce milliseconds. 
     * 
     * @param intervalInHours the number of seconds at which the trigger should repeat.
     * @return the updated customScheduleBuilder
     * @see SimpleTrigger#getRepeatInterval()
     * @see #withRepeatCount(int)
     */
    public CustomScheduleBuilder withIntervalInHours(int intervalInHours) {
        this.interval = intervalInHours * DateBuilder.MILLISECONDS_IN_HOUR;
        return this;
    }
    
    /**
     * Specify a the number of time the trigger will repeat - total number of 
     * firings will be this number + 1. 
     * 
     * @param triggerRepeatCount the number of seconds at which the trigger should repeat.
     * @return the updated customScheduleBuilder
     * @see SimpleTrigger#getRepeatCount()
     * @see #repeatForever()
     */
    public CustomScheduleBuilder withRepeatCount(int triggerRepeatCount) {
        this.repeatCount = triggerRepeatCount;
        return this;
    }
    
    /**
     * Specify that the trigger will repeat indefinitely. 
     * 
     * @return the updated customScheduleBuilder
     * @see SimpleTrigger#getRepeatCount()
     * @see SimpleTrigger#REPEAT_INDEFINITELY
     * @see #withIntervalInMilliseconds(long)
     * @see #withIntervalInSeconds(int)
     * @see #withIntervalInMinutes(int)
     * @see #withIntervalInHours(int)
     */
    public CustomScheduleBuilder repeatForever() {
        this.repeatCount = CustomTrigger.REPEAT_INDEFINITELY;
        return this;
    }

    /**
     * If the Trigger misfires, use the 
     * {@link Trigger#MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY} instruction.
     * 
     * @return the updated CronScheduleBuilder
     * @see Trigger#MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY
     */
    public CustomScheduleBuilder withMisfireHandlingInstructionIgnoreMisfires() {
        misfireInstruction = Trigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY;
        return this;
    }

    /**
     * If the Trigger misfires, use the 
     * {@link SimpleTrigger#MISFIRE_INSTRUCTION_FIRE_NOW} instruction.
     * 
     * @return the updated customScheduleBuilder
     * @see SimpleTrigger#MISFIRE_INSTRUCTION_FIRE_NOW
     */
    
    public CustomScheduleBuilder withMisfireHandlingInstructionFireNow() {
        misfireInstruction = CustomTrigger.MISFIRE_INSTRUCTION_FIRE_NOW;
        return this;
    }

    /**
     * If the Trigger misfires, use the 
     * {@link SimpleTrigger#MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT} instruction.
     * 
     * @return the updated customScheduleBuilder
     * @see SimpleTrigger#MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT
     */
    public CustomScheduleBuilder withMisfireHandlingInstructionNextWithExistingCount() {
        misfireInstruction = CustomTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT;
        return this;
    }
    
    /**
     * If the Trigger misfires, use the 
     * {@link SimpleTrigger#MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT} instruction.
     * 
     * @return the updated customScheduleBuilder
     * @see SimpleTrigger#MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT
     */
    public CustomScheduleBuilder withMisfireHandlingInstructionNextWithRemainingCount() {
        misfireInstruction = CustomTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT;
        return this;
    }

    /**
     * If the Trigger misfires, use the 
     * {@link SimpleTrigger#MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT} instruction.
     * 
     * @return the updated customScheduleBuilder
     * @see SimpleTrigger#MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT
     */
    public CustomScheduleBuilder withMisfireHandlingInstructionNowWithExistingCount() {
        misfireInstruction = CustomTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT;
        return this;
    }
    
    /**
     * If the Trigger misfires, use the 
     * {@link SimpleTrigger#MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT} instruction.
     * 
     * @return the updated customScheduleBuilder
     * @see SimpleTrigger#MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT
     */
    public CustomScheduleBuilder withMisfireHandlingInstructionNowWithRemainingCount() {
        misfireInstruction = CustomTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT;
        return this;
    }

}
