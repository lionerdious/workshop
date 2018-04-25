/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.commons.lang.StringUtils;
import static org.olap4j.metadata.XmlaConstants.Access.Write;

/**
 *
 * @author lionel
 */
public class testMain {

    public static void printName() {

        String fullname = "12345678910";
        String patientname = ((fullname.length() > 10) ? fullname.substring(0, 10) : String.format("%20s", fullname));
        System.out.println(patientname);
    }

    public static void printMemberNumber() {

        String memberNo = "12345678";
        while (memberNo.length() < 8) {
            memberNo = "0" + memberNo;

        }
        System.out.println(memberNo);
    }

    public static void printDOB() {

        String dob = "1993-07-03";
        String DOB = dob.replace("-", "");
        System.out.println(DOB);
    }
    public static void main(String[] args) throws IOException {

        String filler = "lio";
        System.out.println(StringUtils.leftPad("" + filler, 5, '0'));
    }
}
