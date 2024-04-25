package com.lask.view;

import com.lask.model.TaskVisitor;
import com.lask.model.task.BasicTask;
import com.lask.model.task.BooleanTask;
import com.lask.model.task.ComplexTask;
import com.lask.model.task.Task;

public class DisabledPropertyTaskVisitor implements TaskVisitor {

    public static final String PROPERTY_DESCRIPTION = "Description";
    public static final String PROPERTY_END_DATE = "End date";
    public static final String PROPERTY_PRIORITY = "Priority";
    public static final String PROPERTY_DURATION = "Duration";
    public static final String PROPERTY_FINISHED = "Finished";
    public static final String PROPERTY_COMPLETION_PERCENTAGE = "Completion Percentage";

    private final String taskPropertyName;

    private boolean disabled;

    public DisabledPropertyTaskVisitor(String taskPropertyName) {
        this.taskPropertyName = taskPropertyName;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void visit(Task task) {
        task.accept(this);
    }

    @Override
    public void visitBasicTask(BasicTask basicTask) {
        disabled = PROPERTY_FINISHED.equals(taskPropertyName);
    }

    @Override
    public void visitBooleanTask(BooleanTask booleanTask) {
        disabled = PROPERTY_COMPLETION_PERCENTAGE.equals(taskPropertyName);
    }

    @Override
    public void visitComplexTask(ComplexTask complexTask) {
        disabled = !PROPERTY_DESCRIPTION.equals(taskPropertyName) && !PROPERTY_PRIORITY.equals(taskPropertyName);
    }
}
