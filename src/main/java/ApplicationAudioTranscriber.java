import AudioKeyListener.AudioKeyListener;
import AudioRecorder.AudioRecorder;
import AudioTranscriber.AudioTranscriber;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class ApplicationAudioTranscriber {

    public static void main(String[] args) throws NativeHookException {
        int recordToggleKeyCode = (args.length == 0) ? NativeKeyEvent.VC_F2 : getRecordToggleKeyCode(args[0]);
        AudioKeyListener audioKeyListener = new AudioKeyListener(recordToggleKeyCode, new AudioRecorder(), new AudioTranscriber());
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(audioKeyListener);
    }

    private static Integer getRecordToggleKeyCode(String recordToggleKey) {
        Integer recordToggleKeyCode = 0;
        try {
            recordToggleKeyCode = VCMap.getVCCode(recordToggleKey);
        } catch (IllegalArgumentException e) {
            System.err.println("\nInvalid recording toggle key detected. Defaulting to F2's key code.");
            recordToggleKeyCode = NativeKeyEvent.VC_F2;
        }
        finally {
            return recordToggleKeyCode;
        }
    }

}