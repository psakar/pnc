package org.jboss.pnc.core.builder.operationHandlers;

import org.jboss.pnc.core.BuildDriverFactory;
import org.jboss.pnc.core.builder.BuildQueue;
import org.jboss.pnc.core.builder.BuildTask;
import org.jboss.pnc.core.exception.CoreException;
import org.jboss.pnc.model.TaskStatus;
import org.jboss.pnc.spi.builddriver.BuildDriver;

import javax.inject.Inject;
import java.util.function.Consumer;

/**
 * Created by <a href="mailto:matejonnet@gmail.com">Matej Lazar</a> on 2014-12-03.
 */
public class CompleteBuildHandler extends OperationHandlerBase implements OperationHandler {

    BuildDriverFactory buildDriverFactory;

    @Inject
    public CompleteBuildHandler(BuildQueue buildQueue, BuildDriverFactory buildDriverFactory) {
        super(buildQueue);
        this.buildDriverFactory = buildDriverFactory;
    }

    @Override
    protected TaskStatus.Operation executeAfter() {
        return TaskStatus.Operation.BUILD_COMPLETED;
    }

    @Override
    protected void doHandle(BuildTask buildTask) {
        buildTask.onStatusUpdate(new TaskStatus(TaskStatus.Operation.COMPLETING_BUILD, 0)); //TODO rename operations
        try {
            Consumer<String> onComplete = (jobId) -> {
                buildTask.onStatusUpdate(new TaskStatus(TaskStatus.Operation.COMPLETING_BUILD, 100));
            };

            Consumer<Exception> onError = (e) -> {
                buildTask.onError(e);
            };

            //TODO better validation
            assert (buildTask.getProjectBuildConfiguration() != null);
            assert (buildTask.getRepositoryConfiguration() != null);

            BuildDriver buildDriver = buildDriverFactory.getBuildDriver(buildTask.getProjectBuildConfiguration().getEnvironment().getBuildType());
            //TODO get build log
            //TODO get stored artifacts
            //TODO clean up env
            onComplete.accept("id");

        } catch (CoreException e) {
            buildTask.onError(e);
        }
    }
}