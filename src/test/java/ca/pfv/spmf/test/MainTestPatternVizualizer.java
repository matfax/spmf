package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.gui.patternvizualizer.PatternVizualizer;
import org.junit.Test;

/*
 * Copyright (c) 2008-2015 Philippe Fournier-Viger
 *
 * This file is part of the SPMF DATA MINING SOFTWARE
 * (http://www.philippe-fournier-viger.com/spmf).
 *
 * SPMF is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * SPMF is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * SPMF. If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Class for testing the pattern vizualizer window using the "output.txt" file provided in this directory.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestPatternVizualizer {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // the path of the file containing patterns for this test
            String patternFilePath = "test.txt";

            // create the frame
            PatternVizualizer frame = new PatternVizualizer(patternFilePath);
            frame.setVisible(true);
        });
    }
}
