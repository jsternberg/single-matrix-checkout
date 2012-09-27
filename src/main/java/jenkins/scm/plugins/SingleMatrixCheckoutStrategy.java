package jenkins.scm.plugins;

import hudson.Extension;
import hudson.Launcher;
import hudson.matrix.MatrixBuild;
import hudson.matrix.MatrixProject;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import jenkins.scm.SCMCheckoutStrategy;
import jenkins.scm.SCMCheckoutStrategyDescriptor;

import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;

public class SingleMatrixCheckoutStrategy extends SCMCheckoutStrategy {

    @DataBoundConstructor
    public SingleMatrixCheckoutStrategy() {
    }

    @Override
    public void preCheckout(AbstractBuild<?,?> build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException {
        if (build instanceof MatrixBuild)
            super.preCheckout(build, launcher, listener);
    }

    @Override
    public void checkout(AbstractBuild.AbstractBuildExecution execution) throws IOException, InterruptedException {
        if (execution.getBuild() instanceof MatrixBuild)
            super.checkout(execution);
    }

    @Extension
    public static final class DescriptorImpl extends SCMCheckoutStrategyDescriptor {
        public boolean isApplicable(AbstractProject project) {
            return project instanceof MatrixProject;
        }

        @Override
        public String getDisplayName() {
            return "Single Checkout Strategy";
        }
    }
}
