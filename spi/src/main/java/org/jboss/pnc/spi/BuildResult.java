/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.spi;

import org.jboss.pnc.spi.builddriver.BuildDriverResult;
import org.jboss.pnc.spi.environment.RunningEnvironment;
import org.jboss.pnc.spi.repositorymanager.RepositoryManagerResult;

/**
 * Created by <a href="mailto:matejonnet@gmail.com">Matej Lazar</a> on 2015-02-02.
 */
public interface BuildResult {
    BuildDriverResult getBuildDriverResult();

    /**
     * @return Note that RepositoryManagerResult can return nul if build was not successful completed.
     */
    RepositoryManagerResult getRepositoryManagerResult();

    RunningEnvironment getRunningEnvironment();
}
