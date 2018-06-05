/*
 * Copyright (C) 2013-2014 FSquaDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.

 * Author(s): Yury Zhauniarovich
 */

package com.zhauniarovich.fsquadra;

import java.io.File;
import com.beust.jcommander.JCommander;
import com.zhauniarovich.fsquadra.optionsparser.CommandLineOptions;


public class FsquadraMain {

    public static void main(String[] args) {
        CommandLineOptions opts = new CommandLineOptions();
        JCommander jcom = new JCommander(opts, args);
        String path1 = null;
        String path2 = null;
        int pathsNum = opts.paths.size();
        if (pathsNum <= 0) {
            System.out.println("The number of paths cannot be less or equal to 0!");
            jcom.usage();
            return;
        } else if (pathsNum > 2) {
            System.out.println("The number of paths cannot be more than 2!");
            jcom.usage();
            return;
        } else if (pathsNum == 1) {
            path1 = opts.paths.get(0);
            File f = new File(path1);
            if (!f.exists()) {
                System.out.println("The path [" + path1 + "] does not exist!");
                jcom.usage();
                return;
            }
            if (f.isFile()) {
                System.out.println("The path [" + path1 + "] corresponds to a file. If one path is provided it cannot point to file!");
                jcom.usage();
                return;
            }
        } else if (pathsNum == 2) {
            path1 = opts.paths.get(0);
            path2 = opts.paths.get(1);
            File f1 = new File(path1);
            File f2 = new File(path2);
            if (!f1.exists()) {
                System.out.println("The path [" + path1 + "] does not exist!");
                jcom.usage();
                return;
            }
            if (!f2.exists()) {
                System.out.println("The path [" + path2 + "] does not exist!");
                jcom.usage();
                return;
            }
        }
        
        JaccardComparator jc = new JaccardComparator(path1, path2, opts.outputFile);
        jc.compareApks();
    }
}
