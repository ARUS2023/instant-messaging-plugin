package hudson.plugins.im.build_notify;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
// import hudson.model.ResultTrend;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.plugins.im.IMPublisher;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;

import static hudson.plugins.im.tools.BuildHelper.getProjectName;
//import static hudson.plugins.im.tools.BuildHelper.getResultTrend;
//import static hudson.plugins.im.tools.BuildHelper.isFix;

/**
 * {@link BuildToChatNotifier} that skips status and everything except extraMessage.
 * This is probably most useful as a pipeline step.
 *
 */
public class ExtraMessageOnlyBuildToChatNotifier extends BuildToChatNotifier {
    @DataBoundConstructor
    public ExtraMessageOnlyBuildToChatNotifier() {
    }

    @Override
    public String buildStartMessage(IMPublisher publisher, AbstractBuild<?, ?> build, BuildListener listener) throws IOException, InterruptedException {
        return Messages.ExtraMessageOnlyBuildToChatNotifier_StartMessage(
                build.getDisplayName(),getProjectName(build), publisher.getExtraMessage());
    }

    @Override
    public String buildCompletionMessage(IMPublisher publisher, Run<?, ?> run, TaskListener listener) throws IOException, InterruptedException {
        return Messages.ExtraMessageOnlyBuildToChatNotifier_Message(
                getProjectName(run), run.getDisplayName(), publisher.getExtraMessage());
    }

    @Extension
    public static class DescriptorImpl extends BuildToChatNotifierDescriptor {
        public String getDisplayName() {
            return "Extra Message Only";
        }
    }
}
