package com.example.gk.dictionary;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * To handle replacing fragments event
 */
public class ReplaceFragmentCommand extends NavigateCommand {


    public ReplaceFragmentCommand(Fragment fragment, boolean addToBackStack) {
        super(fragment);
        addToBackstack(addToBackStack);
    }

    public static void startNewFragment(FragmentActivity fragmentActivity,
                                        Fragment newFragment, boolean addToBackStack) {
        ReplaceFragmentCommand command = new ReplaceFragmentCommand(newFragment, addToBackStack);
        command.init(fragmentActivity).execute();
    }
}
