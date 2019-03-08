package org.pstcl.ea.service.impl.parallel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.pstcl.ea.dao.IDailyTransactionDao;
import org.pstcl.ea.dao.IFileMasterDao;
import org.pstcl.ea.dao.IInstantRegistersDao;
import org.pstcl.ea.dao.ILoadSurveyTransactionDao;
import org.pstcl.ea.dao.ILocationMasterDao;
import org.pstcl.ea.dao.IMeterMasterDao;
import org.pstcl.ea.dao.ITamperLogDao;
import org.pstcl.ea.model.CMRIFileDataModel;
import org.pstcl.ea.model.entity.DailyTransaction;
import org.pstcl.ea.model.entity.EAUser;
import org.pstcl.ea.model.entity.FileMaster;
import org.pstcl.ea.model.entity.InstantRegisters;
import org.pstcl.ea.model.entity.LoadSurveyTransaction;
import org.pstcl.ea.model.entity.LocationMaster;
import org.pstcl.ea.model.entity.TamperLogTransaction;
import org.pstcl.ea.service.impl.UserServiceImpl;
import org.pstcl.ea.util.DateUtil;
import org.pstcl.ea.util.EAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Scope("prototype")
public class DataReaderThread {

	//added
	@Autowired
	protected IInstantRegistersDao instantRegistersDao;
	
	
	
	// method currently added not tested
		private String processInstantRegistersTable(CMRIFileDataModel cmriFileDataModel, File fileToRead,
				BufferedReader buf) throws IOException, ParseException {

			FileMaster fileDetails = cmriFileDataModel.getFileMaster();

			String fileName = FilenameUtils.getBaseName(fileToRead.getName());

			// Define Variables
			LocationMaster location = null;
			Date date = null;
			BigDecimal phaseAVoltage = null, phaseBVoltage = null, phaseCVoltage = null, phaseACurrent = null;
			BigDecimal phaseBCurrent = null;
			BigDecimal phaseCCurrent = null;
			BigDecimal phaseAVoltAngle = null;
			BigDecimal phaseBVoltAngle = null;
			BigDecimal phaseCVoltAngle = null;
			BigDecimal phaseACurrentAngle = null;
			BigDecimal phaseBCurrentAngle = null;
			BigDecimal phaseCCurrentAngle = null;
			BigDecimal threePhasePF = null;
			BigDecimal frequency = null;
			BigDecimal activePowerA = null;
			BigDecimal activePowerB = null;
			BigDecimal activePowerC = null;
			BigDecimal activePowerTotal = null;
			BigDecimal activePowerFunA = null;
			BigDecimal activePowerFunB = null;
			BigDecimal activePowerFunC = null;
			BigDecimal activePowerFunTotal = null;
			BigDecimal apparentPowerA = null;
			BigDecimal apparentPowerB = null;
			BigDecimal apparentPowerC = null;
			BigDecimal apparentPowerTotal = null;
			BigDecimal reactivePowerA = null;
			BigDecimal reactivePowerB = null;
			BigDecimal reactivePowerC = null;
			BigDecimal reactivePowerTotal = null;
			BigDecimal touChannel1Unified = null;
			BigDecimal touChannel1Rate1 = null;
			BigDecimal touChannel1Rate2 = null;
			BigDecimal touChannel1Rate3 = null;
			BigDecimal touChannel1Rate4 = null;
			BigDecimal touChannel1Rate5 = null;
			BigDecimal touChannel2Unified = null;
			BigDecimal touChannel2Rate1 = null;
			BigDecimal touChannel2Rate2 = null;
			BigDecimal touChannel2Rate3 = null;
			BigDecimal touChannel2Rate4 = null;
			BigDecimal touChannel2Rate5 = null;
			BigDecimal touChannel3Unified = null;
			BigDecimal touChannel3Rate1 = null;
			BigDecimal touChannel3Rate2 = null;
			BigDecimal touChannel3Rate3 = null;
			BigDecimal touChannel3Rate4 = null;
			BigDecimal touChannel3Rate5 = null;
			BigDecimal touChannel4Unified = null;
			BigDecimal touChannel4Rate1 = null;
			BigDecimal touChannel4Rate2 = null;
			BigDecimal touChannel4Rate3 = null;
			BigDecimal touChannel4Rate4 = null;
			BigDecimal touChannel4Rate5 = null;
			BigDecimal touChannel5Unified = null;
			BigDecimal touChannel5Rate1 = null;
			BigDecimal touChannel5Rate2 = null;
			BigDecimal touChannel5Rate3 = null;
			BigDecimal touChannel5Rate4 = null;
			BigDecimal touChannel5Rate5 = null;
			BigDecimal touChannel6Unified = null;
			BigDecimal touChannel6Rate1 = null;
			BigDecimal touChannel6Rate2 = null;
			BigDecimal touChannel6Rate3 = null;
			BigDecimal touChannel6Rate4 = null;
			BigDecimal touChannel6Rate5 = null;
			BigDecimal touChannel7Unified = null;
			BigDecimal touChannel7Rate1 = null;
			BigDecimal touChannel7Rate2 = null;
			BigDecimal touChannel7Rate3 = null;
			BigDecimal touChannel7Rate4 = null;
			BigDecimal touChannel7Rate5 = null;
			BigDecimal touChannel8Unified = null;
			BigDecimal touChannel8Rate1 = null;
			BigDecimal touChannel8Rate2 = null;
			BigDecimal touChannel8Rate3 = null;
			BigDecimal touChannel8Rate4 = null;
			BigDecimal touChannel8Rate5 = null;
			BigDecimal touChannel9Unified = null;
			BigDecimal touChannel10Unified = null;
			BigDecimal touChannel11Unified = null;
			BigDecimal touChannel12Unified = null;
			BigDecimal demandTouChannel1Rate1 = null;
			BigDecimal demandTouChannel1Rate2 = null;
			BigDecimal demandTouChannel1Rate3 = null;
			BigDecimal demandTouChannel1Rate4 = null;
			BigDecimal demandTouChannel1Rate5 = null;
			BigDecimal demandTouChannel2Rate1 = null;
			BigDecimal demandTouChannel2Rate2 = null;
			BigDecimal demandTouChannel2Rate3 = null;
			BigDecimal demandTouChannel2Rate4 = null;
			BigDecimal demandTouChannel2Rate5 = null;
			BigDecimal demandTouChannel3Rate1 = null;
			BigDecimal demandTouChannel3Rate2 = null;
			BigDecimal demandTouChannel3Rate3 = null;
			BigDecimal demandTouChannel3Rate4 = null;
			BigDecimal demandTouChannel3Rate5 = null;
			BigDecimal demandTouChannel4Rate1 = null;
			BigDecimal demandTouChannel4Rate2 = null;
			BigDecimal demandTouChannel4Rate3 = null;
			BigDecimal demandTouChannel4Rate4 = null;
			BigDecimal demandTouChannel4Rate5 = null;
			BigDecimal cTPrimary = null;
			BigDecimal cTSecondary = null;
			BigDecimal vTPrimary = null;
			BigDecimal vTSecondary = null;
			BigDecimal demandChannel1Unified = null;
			BigDecimal demandChannel2Unified = null;
			BigDecimal demandChannel3Unified = null;
			BigDecimal demandChannel4Unified = null;
			Date dateChannel1Unified = null, dateChannel2Unified = null, dateChannel1Rate1 = null, dateChannel1Rate2 = null,
					dateChannel1Rate3 = null, dateChannel1Rate4 = null, dateChannel1Rate5 = null, dateChannel2Rate1 = null,
					dateChannel2Rate2 = null, dateChannel2Rate3 = null, dateChannel2Rate4 = null, dateChannel2Rate5 = null,
					dateChannel3Unified = null, dateChannel4Unified = null;
			long powerOnHours = 0, powerOffHours = 0, mDResetCode = 0;
			Integer element = -1;
			String batteryStatus = "", rtcStatus = "", nonVolatileStatus = "", displaySegmentStatus = "";

			while (true) {
				String instantRegisterLineJustFetched = buf.readLine();

				// ReturnCases
				if (instantRegisterLineJustFetched == null || instantRegisterLineJustFetched.length() == 0
						|| instantRegisterLineJustFetched.contains("DAILY_SURVEY")
						|| instantRegisterLineJustFetched.contains("LOAD_SURVEY")
						|| instantRegisterLineJustFetched.contains("TAMPER_LOG")
						|| instantRegisterLineJustFetched.contains("METER_INFO")) {
					saveInstantRegisterDetails(cmriFileDataModel, fileName, location, date, phaseAVoltage, phaseBVoltage,
							phaseCVoltage, phaseACurrent, phaseBCurrent, phaseCCurrent, phaseAVoltAngle, phaseBVoltAngle,
							phaseCVoltAngle, phaseACurrentAngle, phaseBCurrentAngle, phaseCCurrentAngle, threePhasePF,
							frequency, activePowerA, activePowerB, activePowerC, activePowerTotal, activePowerFunA,
							activePowerFunB, activePowerFunC, activePowerFunTotal, apparentPowerA, apparentPowerB,
							apparentPowerC, apparentPowerTotal, reactivePowerA, reactivePowerB, reactivePowerC,
							reactivePowerTotal, touChannel1Unified, touChannel1Rate1, touChannel1Rate2, touChannel1Rate3,
							touChannel1Rate4, touChannel1Rate5, touChannel2Unified, touChannel2Rate1, touChannel2Rate2,
							touChannel2Rate3, touChannel2Rate4, touChannel2Rate5, touChannel3Unified, touChannel3Rate1,
							touChannel3Rate2, touChannel3Rate3, touChannel3Rate4, touChannel3Rate5, touChannel4Unified,
							touChannel4Rate1, touChannel4Rate2, touChannel4Rate3, touChannel4Rate4, touChannel4Rate5,
							touChannel5Unified, touChannel5Rate1, touChannel5Rate2, touChannel5Rate3, touChannel5Rate4,
							touChannel5Rate5, touChannel6Unified, touChannel6Rate1, touChannel6Rate2, touChannel6Rate3,
							touChannel6Rate4, touChannel6Rate5, touChannel7Unified, touChannel7Rate1, touChannel7Rate2,
							touChannel7Rate3, touChannel7Rate4, touChannel7Rate5, touChannel8Unified, touChannel8Rate1,
							touChannel8Rate2, touChannel8Rate3, touChannel8Rate4, touChannel8Rate5, touChannel9Unified,
							touChannel10Unified, touChannel11Unified, touChannel12Unified, demandTouChannel1Rate1,
							demandTouChannel1Rate2, demandTouChannel1Rate3, demandTouChannel1Rate4, demandTouChannel1Rate5,
							demandTouChannel2Rate1, demandTouChannel2Rate2, demandTouChannel2Rate3, demandTouChannel2Rate4,
							demandTouChannel2Rate5, demandTouChannel3Rate1, demandTouChannel3Rate2, demandTouChannel3Rate3,
							demandTouChannel3Rate4, demandTouChannel3Rate5, demandTouChannel4Rate1, demandTouChannel4Rate2,
							demandTouChannel4Rate3, demandTouChannel4Rate4, demandTouChannel4Rate5, cTPrimary, cTSecondary,
							vTPrimary, vTSecondary, demandChannel1Unified, demandChannel2Unified, demandChannel3Unified,
							demandChannel4Unified, dateChannel1Unified, dateChannel2Unified, dateChannel1Rate1,
							dateChannel1Rate2, dateChannel1Rate3, dateChannel1Rate4, dateChannel1Rate5, dateChannel2Rate1,
							dateChannel2Rate2, dateChannel2Rate3, dateChannel2Rate4, dateChannel2Rate5, dateChannel3Unified,
							dateChannel4Unified, powerOnHours, powerOffHours, mDResetCode, element, batteryStatus,
							rtcStatus, nonVolatileStatus, displaySegmentStatus);

					return instantRegisterLineJustFetched;
				} else {

					String[] linep = instantRegisterLineJustFetched.split("\t");
					linep[2]=linep[2].trim();
					
					SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					if (linep[0].equalsIgnoreCase("Date/Time")) {
						date = parser.parse(linep[2]);
						fileDetails.setTransactionDate(date);

					} 
					else if (linep[0].equalsIgnoreCase("Phase A Voltage")) {
						phaseAVoltage = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Phase B Voltage")) {
						phaseBVoltage = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Phase C Voltage")) {
						phaseCVoltage = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Phase A Current")) {
						phaseACurrent = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Phase B Current")) {
						phaseBCurrent = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Phase C Current")) {
						phaseCCurrent = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Phase A Current Angle")) {
						phaseACurrentAngle = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Phase B Current Angle")) {
						phaseBCurrentAngle = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Phase C Current Angle")) {
						phaseCCurrentAngle = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Phase A Volt Angle")) {
						phaseAVoltAngle = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Phase B Volt Angle")) {
						phaseBVoltAngle = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Phase C Volt Angle")) {
						phaseCVoltAngle = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Three Phase PF")) {
						threePhasePF = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Frequency")) {
						frequency = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Active Power A")) {
						activePowerA = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Active Power B")) {
						activePowerB = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Active Power C")) {
						activePowerC = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Active Power Total")) {
						activePowerTotal = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Active Power Fun A")) {
						activePowerFunA = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Active Power Fun B")) {
						activePowerFunB = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Active Power Fun C")) {
						activePowerFunC = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Active Power Fun Total")) {
						activePowerFunTotal = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Apparent Power A")) {
						apparentPowerA = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Apparent Power B")) {
						apparentPowerB = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Apparent Power C")) {
						apparentPowerC = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Apparent Power Total")) {
						apparentPowerTotal = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Reactive Power A")) {
						reactivePowerA = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Reactive Power B")) {
						reactivePowerB = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Reactive Power C")) {
						reactivePowerC = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Reactive Power Total")) {
						reactivePowerTotal = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 1 Unified")) {
						
						touChannel1Unified=new BigDecimal(linep[2]);
					//	System.out.println(a);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 1 Rate1")) {
						
						touChannel1Rate1 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 1 Rate2")) {
						touChannel1Rate2 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 1 Rate3")) {
						touChannel1Rate3 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 1 Rate4")) {
						touChannel1Rate4 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 1 Rate5")) {
						touChannel1Rate5 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 2 Unified")) {
						touChannel2Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 2 Rate1")) {
						touChannel2Rate1 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 2 Rate2")) {
						touChannel2Rate2 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 2 Rate3")) {
						touChannel2Rate3 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 2 Rate4")) {
						touChannel2Rate4 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 2 Rate5")) {
						touChannel2Rate5 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 3 Unified")) {
						touChannel3Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 3 Rate1")) {
						touChannel3Rate1 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 3 Rate2")) {
						touChannel3Rate2 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 3 Rate3")) {
						touChannel3Rate3 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 3 Rate4")) {
						touChannel3Rate4 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 3 Rate5")) {
						touChannel3Rate5 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 4 Unified")) {
						touChannel4Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 4 Rate1")) {
						touChannel4Rate1 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 4 Rate2")) {
						touChannel4Rate2 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 4 Rate3")) {
						touChannel4Rate3 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 4 Rate4")) {
						touChannel4Rate4 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 4 Rate5")) {
						touChannel4Rate5 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 5 Unified")) {
						touChannel5Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 5 Rate1")) {
						touChannel5Rate1 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 5 Rate2")) {
						touChannel5Rate2 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 5 Rate3")) {
						touChannel5Rate3 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 5 Rate4")) {
						touChannel5Rate4 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 5 Rate5")) {
						touChannel5Rate5 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 6 Unified")) {
						touChannel6Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 6 Rate1")) {
						touChannel6Rate1 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 6 Rate2")) {
						touChannel6Rate2 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 6 Rate3")) {
						touChannel6Rate3 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 6 Rate4")) {
						touChannel6Rate4 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 6 Rate5")) {
						touChannel6Rate5 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 7 Unified")) {
						touChannel7Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 7 Rate1")) {
						touChannel7Rate1 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 7 Rate2")) {
						touChannel7Rate2 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 7 Rate3")) {
						touChannel7Rate3 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 7 Rate4")) {
						touChannel7Rate4 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 7 Rate5")) {
						touChannel7Rate5 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 8 Unified")) {
						touChannel8Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 8 Rate1")) {
						touChannel8Rate1 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 8 Rate2")) {
						touChannel8Rate2 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 8 Rate3")) {
						touChannel8Rate3 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 8 Rate4")) {
						touChannel8Rate4 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 8 Rate5")) {
						touChannel8Rate5 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 9 Unified")) {
						touChannel9Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 10 Unified")) {
						touChannel10Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 11 Unified")) {
						touChannel11Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("TOU Channel 12 Unified")) {
						touChannel12Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand Channel 1 Unified")) {
						demandChannel1Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand Channel 2 Unified")) {
						demandChannel2Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand Channel 3 Unified")) {
						demandChannel3Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand Channel 4 Unified")) {
						demandChannel4Unified = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 1 Rate1")) {
						demandTouChannel1Rate1 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 1 Rate2")) {
						demandTouChannel1Rate2 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 1 Rate3")) {
						demandTouChannel1Rate3 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 1 Rate4")) {
						demandTouChannel1Rate4 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 1 Rate5")) {
						demandTouChannel1Rate5 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 2 Rate1")) {
						demandTouChannel2Rate1 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 2 Rate2")) {
						demandTouChannel2Rate2 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 2 Rate3")) {
						demandTouChannel2Rate3 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 2 Rate4")) {
						demandTouChannel2Rate4 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 2 Rate5")) {
						demandTouChannel2Rate5 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 3 Rate1")) {
						demandTouChannel3Rate1 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 3 Rate2")) {
						demandTouChannel3Rate2 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 3 Rate3")) {
						demandTouChannel3Rate3 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 3 Rate4")) {
						demandTouChannel3Rate4 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 3 Rate5")) {
						demandTouChannel3Rate5 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 4 Rate1")) {
						demandTouChannel4Rate1 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 4 Rate2")) {
						demandTouChannel4Rate2 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 4 Rate3")) {
						demandTouChannel4Rate3 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 4 Rate4")) {
						demandTouChannel4Rate4 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Demand TOU Channel 4 Rate5")) {
						demandTouChannel4Rate5 = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Date Channel 1 Unified")) {
						dateChannel1Unified = parser.parse(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Date Channel 2 Unified")) {
						dateChannel2Unified = parser.parse(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Date Channel 3 Unified")) {
						dateChannel3Unified = parser.parse(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Date Channel 4 Unified")) {
						dateChannel4Unified = parser.parse(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Date Channel 1 Rate1")) {
						dateChannel1Rate1 = parser.parse(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Date Channel 1 Rate2")) {
						dateChannel1Rate2 = parser.parse(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Date Channel 1 Rate3")) {
						dateChannel1Rate3 = parser.parse(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Date Channel 1 Rate4")) {
						dateChannel1Rate4 = parser.parse(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Date Channel 1 Rate5")) {
						dateChannel1Rate5 = parser.parse(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Date Channel 2 Rate1")) {
						dateChannel2Rate1 = parser.parse(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Date Channel 2 Rate2")) {
						dateChannel2Rate2 = parser.parse(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Date Channel 2 Rate3")) {
						dateChannel2Rate3 = parser.parse(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Date Channel 2 Rate4")) {
						dateChannel2Rate4 = parser.parse(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Date Channel 2 Rate5")) {
						dateChannel2Rate5 = parser.parse(linep[2]);
					} else if (linep[0].equalsIgnoreCase("MD Reset Count")) {
						mDResetCode = Integer.parseInt(linep[2]);
					} else if (linep[0].equalsIgnoreCase("CT Primary")) {
						cTPrimary = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("CT Secondary")) {
						cTSecondary = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("VT Primary")) {
						vTPrimary = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("VT Secondary")) {
						vTSecondary = new BigDecimal(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Power On Hours")) {
						powerOnHours = Long.parseLong(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Power Off Hours")) {
						powerOffHours = Long.parseLong(linep[2]);
					} else if (linep[0].equalsIgnoreCase("Battery Status")) {
						batteryStatus = linep[2];
					} else if (linep[0].equalsIgnoreCase("RTC Status")) {
						rtcStatus = linep[2];
					} else if (linep[0].equalsIgnoreCase("Display Segment Status")) {
						displaySegmentStatus = linep[2];
					} else if (linep[0].equalsIgnoreCase("Non Volatile Status")) {
						nonVolatileStatus = linep[2];
					} else if (linep[0].equalsIgnoreCase("Element")) {
						element = Integer.parseInt(linep[2]);
					}
				}

			}
		}

		public void saveInstantRegisterDetails(CMRIFileDataModel cmriFileDataModel, String fileName,
				LocationMaster location, Date date, BigDecimal phaseAVoltage, BigDecimal phaseBVoltage,
				BigDecimal phaseCVoltage, BigDecimal phaseACurrent, BigDecimal phaseBCurrent, BigDecimal phaseCCurrent,
				BigDecimal phaseAVoltAngle, BigDecimal phaseBVoltAngle, BigDecimal phaseCVoltAngle,
				BigDecimal phaseACurrentAngle, BigDecimal phaseBCurrentAngle, BigDecimal phaseCCurrentAngle,
				BigDecimal threePhasePF, BigDecimal frequency, BigDecimal activePowerA, BigDecimal activePowerB,
				BigDecimal activePowerC, BigDecimal activePowerTotal, BigDecimal activePowerFunA,
				BigDecimal activePowerFunB, BigDecimal activePowerFunC, BigDecimal activePowerFunTotal,
				BigDecimal apparentPowerA, BigDecimal apparentPowerB, BigDecimal apparentPowerC,
				BigDecimal apparentPowerTotal, BigDecimal reactivePowerA, BigDecimal reactivePowerB,
				BigDecimal reactivePowerC, BigDecimal reactivePowerTotal, BigDecimal touChannel1Unified,
				BigDecimal touChannel1Rate1, BigDecimal touChannel1Rate2, BigDecimal touChannel1Rate3,
				BigDecimal touChannel1Rate4, BigDecimal touChannel1Rate5, BigDecimal touChannel2Unified,
				BigDecimal touChannel2Rate1, BigDecimal touChannel2Rate2, BigDecimal touChannel2Rate3,
				BigDecimal touChannel2Rate4, BigDecimal touChannel2Rate5, BigDecimal touChannel3Unified,
				BigDecimal touChannel3Rate1, BigDecimal touChannel3Rate2, BigDecimal touChannel3Rate3,
				BigDecimal touChannel3Rate4, BigDecimal touChannel3Rate5, BigDecimal touChannel4Unified,
				BigDecimal touChannel4Rate1, BigDecimal touChannel4Rate2, BigDecimal touChannel4Rate3,
				BigDecimal touChannel4Rate4, BigDecimal touChannel4Rate5, BigDecimal touChannel5Unified,
				BigDecimal touChannel5Rate1, BigDecimal touChannel5Rate2, BigDecimal touChannel5Rate3,
				BigDecimal touChannel5Rate4, BigDecimal touChannel5Rate5, BigDecimal touChannel6Unified,
				BigDecimal touChannel6Rate1, BigDecimal touChannel6Rate2, BigDecimal touChannel6Rate3,
				BigDecimal touChannel6Rate4, BigDecimal touChannel6Rate5, BigDecimal touChannel7Unified,
				BigDecimal touChannel7Rate1, BigDecimal touChannel7Rate2, BigDecimal touChannel7Rate3,
				BigDecimal touChannel7Rate4, BigDecimal touChannel7Rate5, BigDecimal touChannel8Unified,
				BigDecimal touChannel8Rate1, BigDecimal touChannel8Rate2, BigDecimal touChannel8Rate3,
				BigDecimal touChannel8Rate4, BigDecimal touChannel8Rate5, BigDecimal touChannel9Unified,
				BigDecimal touChannel10Unified, BigDecimal touChannel11Unified, BigDecimal touChannel12Unified,
				BigDecimal demandTouChannel1Rate1, BigDecimal demandTouChannel1Rate2, BigDecimal demandTouChannel1Rate3,
				BigDecimal demandTouChannel1Rate4, BigDecimal demandTouChannel1Rate5, BigDecimal demandTouChannel2Rate1,
				BigDecimal demandTouChannel2Rate2, BigDecimal demandTouChannel2Rate3, BigDecimal demandTouChannel2Rate4,
				BigDecimal demandTouChannel2Rate5, BigDecimal demandTouChannel3Rate1, BigDecimal demandTouChannel3Rate2,
				BigDecimal demandTouChannel3Rate3, BigDecimal demandTouChannel3Rate4, BigDecimal demandTouChannel3Rate5,
				BigDecimal demandTouChannel4Rate1, BigDecimal demandTouChannel4Rate2, BigDecimal demandTouChannel4Rate3,
				BigDecimal demandTouChannel4Rate4, BigDecimal demandTouChannel4Rate5, BigDecimal cTPrimary,
				BigDecimal cTSecondary, BigDecimal vTPrimary, BigDecimal vTSecondary, BigDecimal demandChannel1Unified,
				BigDecimal demandChannel2Unified, BigDecimal demandChannel3Unified, BigDecimal demandChannel4Unified,
				Date dateChannel1Unified, Date dateChannel2Unified, Date dateChannel1Rate1, Date dateChannel1Rate2,
				Date dateChannel1Rate3, Date dateChannel1Rate4, Date dateChannel1Rate5, Date dateChannel2Rate1,
				Date dateChannel2Rate2, Date dateChannel2Rate3, Date dateChannel2Rate4, Date dateChannel2Rate5,
				Date dateChannel3Unified, Date dateChannel4Unified, long powerOnHours, long powerOffHours, long mDResetCode,
				Integer element, String batteryStatus, String rtcStatus, String nonVolatileStatus,
				String displaySegmentStatus) {
			InstantRegisters register = new InstantRegisters(cmriFileDataModel.getFileMaster().getLocation(), date, phaseAVoltage, phaseBVoltage, phaseCVoltage,
					phaseACurrent, phaseBCurrent, phaseCCurrent, phaseAVoltAngle, phaseBVoltAngle, phaseCVoltAngle,
					phaseACurrentAngle, phaseBCurrentAngle, phaseCCurrentAngle, threePhasePF, frequency, activePowerA,
					activePowerB, activePowerC, activePowerTotal,

					activePowerFunA, activePowerFunB, activePowerFunC, activePowerFunTotal,

					apparentPowerA, apparentPowerB, apparentPowerC, apparentPowerTotal,

					reactivePowerA, reactivePowerB, reactivePowerC, reactivePowerTotal,

					touChannel1Unified, touChannel1Rate1, touChannel1Rate2, touChannel1Rate3, touChannel1Rate4,
					touChannel1Rate5,

					touChannel2Unified, touChannel2Rate1, touChannel2Rate2, touChannel2Rate3, touChannel2Rate4,
					touChannel2Rate5,

					touChannel3Unified, touChannel3Rate1, touChannel3Rate2, touChannel3Rate3, touChannel3Rate4,
					touChannel3Rate5, touChannel4Unified, touChannel4Rate1, touChannel4Rate2, touChannel4Rate3,
					touChannel4Rate4, touChannel4Rate5, touChannel5Unified, touChannel5Rate1, touChannel5Rate2,
					touChannel5Rate3, touChannel5Rate4, touChannel5Rate5, touChannel6Unified, touChannel6Rate1,
					touChannel6Rate2, touChannel6Rate3, touChannel6Rate4, touChannel6Rate5, touChannel7Unified,
					touChannel7Rate1, touChannel7Rate2, touChannel7Rate3, touChannel7Rate4, touChannel7Rate5,
					touChannel8Unified, touChannel8Rate1, touChannel8Rate2, touChannel8Rate3, touChannel8Rate4,
					touChannel8Rate5,

					touChannel9Unified, touChannel10Unified, touChannel11Unified, touChannel12Unified,
					demandChannel1Unified, demandChannel2Unified, demandChannel3Unified, demandChannel4Unified,
					demandTouChannel1Rate1, demandTouChannel1Rate2, demandTouChannel1Rate3, demandTouChannel1Rate4,
					demandTouChannel1Rate5, demandTouChannel2Rate1, demandTouChannel2Rate2, demandTouChannel2Rate3,
					demandTouChannel2Rate4, demandTouChannel2Rate5, demandTouChannel3Rate1, demandTouChannel3Rate2,
					demandTouChannel3Rate3, demandTouChannel3Rate4, demandTouChannel3Rate5, demandTouChannel4Rate1,
					demandTouChannel4Rate2, demandTouChannel4Rate3, demandTouChannel4Rate4, demandTouChannel4Rate5,
					dateChannel1Unified, dateChannel2Unified, dateChannel1Rate1, dateChannel1Rate2, dateChannel1Rate3,
					dateChannel1Rate4, dateChannel1Rate5, dateChannel2Rate1, dateChannel2Rate2, dateChannel2Rate3,
					dateChannel2Rate4, dateChannel2Rate5, dateChannel3Unified, dateChannel4Unified, mDResetCode, cTPrimary,
					cTSecondary, vTPrimary, vTSecondary, powerOnHours, powerOffHours, element, batteryStatus, rtcStatus,
					nonVolatileStatus, displaySegmentStatus, fileName);
			List <InstantRegisters> ir = new ArrayList <InstantRegisters>();
			ir.add(register);
			cmriFileDataModel.setInstantRegistersDetails(ir);
		}

	
		//added by leevansha
		public void saveInstantRegisterData(CMRIFileDataModel cmriFileDataModel) {
			if (cmriFileDataModel.getFileMaster().getFileActionStatus().equals(EAUtil.FILE_ACTION__APPROVED_AE)
					&& null != cmriFileDataModel.getFileMaster().getLocation()) {
				// dailyTransactionDao.save(cmriFileDataModel.getDailyTransactions(),
				// getLoggedInUser());
				// loadSurveyTransactionDao.save(cmriFileDataModel.getLoadSurveyTransactions(),
				// getLoggedInUser());
				instantRegistersDao.save(cmriFileDataModel.getInstantRegistersDetails(), getLoggedInUser());
				saveFileDetails(cmriFileDataModel.getFileMaster());

			} else {
				saveFileDetails(cmriFileDataModel.getFileMaster());
				// fileMasterDao.update(cmriFileDataModel.getFileMaster(), getLoggedInUser());
			}
		}
	
	
	//
	// @Override
	// public void run() {
	// FileMaster fileDetails= processTXTFile(textFile);
	//
	// if (null != fileDetails.getMeterMaster()) {
	// saveFileDetails(fileDetails);
	// }
	//
	//// System.out.println(
	// // Thread.currentThread());
	// // System.out.println("ID"+
	// // Thread.currentThread().getId());
	// //
	// //
	// // System.out.println("NAME"+
	// // Thread.currentThread().getName());
	//
	// }

	@Autowired
	protected ITamperLogDao tamperLogDao;

	@Autowired
	protected IDailyTransactionDao dailyTransactionDao;

	@Autowired
	IFileMasterDao fileMasterDao;

	@Autowired
	protected ILoadSurveyTransactionDao loadSurveyTransactionDao;

	@Autowired
	protected ILocationMasterDao locationMasterDao;

	@Autowired
	protected IMeterMasterDao meterDao;

	private FileMaster textFile;

	public EAUser getLoggedInUser() {
		return null;
	}

	public FileMaster getTextFile() {
		return textFile;
	}

	public void getFileMetaData(FileMaster fileDetails) {

		File fileToRead = new File(fileDetails.getTxtfileName());

		String meterNoAtTop = "";
		try {

			BufferedReader buf = new BufferedReader(new FileReader(fileToRead));

			String lineJustFetched = null;

			while (true) {
				lineJustFetched = buf.readLine();

				if (lineJustFetched == null) {

					break;
				} else {

					if (lineJustFetched.contains("PlantNumber")) {

						fileDetails = processPlantInfo(fileDetails, lineJustFetched);
					} else if (lineJustFetched.contains("Date/Time") && !lineJustFetched.contains("Record")) {

						String[] dateTimeData = lineJustFetched.split("\t");
						// Date/Time 0x0000F03D 01/10/18 13:32:27
						SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
						// output format: yyyy-MM-dd
						// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

						fileDetails.setTransactionDate(parser.parse(dateTimeData[2]));
						// fileDetails=fileMasterDao.findByLocationDateCombo(fileDetails.getLocation(),
						// fileDetails.getTransactionDate());

					} else if (lineJustFetched.contains("METER_INFO")) {

						lineJustFetched = processMeterInfo(fileDetails, buf);

					}
				}
			}
			buf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private DailyTransaction processDailySurveyRow(File fileToRead, FileMaster fileMaster, Integer indexOfRecordNo,
			Integer indexOfImport, Integer indexOfExport, Integer indexOfcumulativeNetWh, Integer indexOfDateTime,
			String[] dailyRecord) throws ParseException {
		if (StringUtils.isNumeric(dailyRecord[0]) && dailyRecord[0].length() > 0) {
			DailyTransaction dailyTransaction = new DailyTransaction();
			dailyTransaction.setLocation(fileMaster.getLocation());
			String dailyTransactionDate = dailyRecord[indexOfDateTime];
			dailyTransaction.setFileName(FilenameUtils.getBaseName(fileToRead.getName()));

			dailyTransaction.setRecordNo(Integer.parseInt(dailyRecord[indexOfRecordNo]));

			dailyTransaction
			.setTransactionDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dailyTransactionDate));

			if (indexOfImport > 0) {
				String dailyRecordImport = dailyRecord[indexOfImport].trim();
				dailyTransaction.setImportWHF(new BigDecimal(dailyRecordImport));
			}
			if (indexOfExport > 0) {
				String dailyRecordExport = dailyRecord[indexOfExport].trim();

				dailyTransaction.setExportWHF(new BigDecimal(dailyRecordExport));
			}

			if (indexOfcumulativeNetWh > 0) {
				String dailyRecordCumuMh = dailyRecord[indexOfcumulativeNetWh];
				dailyTransaction.setCumulativeNetWh(Double.parseDouble(dailyRecordCumuMh));
			}

			fileMaster.setDailyRecordNoEnd(dailyTransaction.getRecordNo());
			// System.out.println("PRINTED AFTER UPDATE");
			return dailyTransaction;
			// saveDailyTransaction(dailyTransaction);
		}
		return null;
	}

	private String processDailySurveyTable(CMRIFileDataModel cmriFileDataModel, File fileToRead, BufferedReader buf)
			throws IOException, ParseException {
		Integer startRecordNo = -1;
		Integer indexOfImport = -1;
		Integer indexOfExport = -1;

		Integer indexOfRecordNo = -1;
		Integer indexOfcumulativeNetWh = -1;
		Integer indexOfDateTime = 1;

		FileMaster fileDetails = cmriFileDataModel.getFileMaster();

		List<DailyTransaction> dailyTransactions = new ArrayList<DailyTransaction>();

		while (true) {
			String dailySurveylineJustFetched = buf.readLine();

			String headerNamesDailySurvey[];

			if (dailySurveylineJustFetched == null) {

				return dailySurveylineJustFetched;

			} else if (dailySurveylineJustFetched.contains("StartRecord")) {

				String[] startRecordData = dailySurveylineJustFetched.split("\t");
				startRecordNo = Integer.parseInt(startRecordData[1]);

			} else if (dailySurveylineJustFetched.contains("Import Wh (Fund) Total")) {

				headerNamesDailySurvey = dailySurveylineJustFetched.split("\t");
				indexOfRecordNo = Arrays.asList(headerNamesDailySurvey).indexOf("Record No.");

				indexOfImport = Arrays.asList(headerNamesDailySurvey).indexOf("Import Wh (Fund) Total");
				indexOfExport = Arrays.asList(headerNamesDailySurvey).indexOf("Export Wh (Fund) Total");
				indexOfcumulativeNetWh = Arrays.asList(headerNamesDailySurvey).indexOf("Cum Net Wh");
				// indexOfcumulativeNetWh=Arrays.asList(headerNames).indexOf("Cum Net Wh");
				// Arrays.asList(headerNames).indexOf("Import Wh (Fund) Total");

			}
			if (startRecordNo >= 0 && indexOfImport > 0 && indexOfExport > 0) {

				fileDetails.setDailyRecordNoStart(startRecordNo);
				while (true) {
					String dailySurveyRecordJustFetched = buf.readLine();

					if (dailySurveyRecordJustFetched == null) {
						if (null == fileDetails.getDailyRecordNoEnd()) {
							fileDetails.setDailyRecordNoEnd(0);
							fileDetails.setDailyRecordCount(0);
						} else {

							fileDetails.setDailyRecordCount(fileDetails.getDailyRecordNoEnd() - startRecordNo + 1);
						}
						cmriFileDataModel.setDailyTransactions(dailyTransactions);
						// dailyTransactionDao.save(dailyTransactions, getLoggedInUser());

						return dailySurveylineJustFetched;
					} else {

						String[] dailyRecord = dailySurveyRecordJustFetched.split("\t");

						DailyTransaction dailyTransaction = processDailySurveyRow(fileToRead, fileDetails,
								indexOfRecordNo, indexOfImport, indexOfExport, indexOfcumulativeNetWh, indexOfDateTime,
								dailyRecord);
						if (null != dailyTransaction) {
							if (isDailyTransactionInTheMonth(dailyTransaction.getTransactionDate(),
									fileDetails.getTransactionDate())) {
								dailyTransactions.add(dailyTransaction);

							}
						}
					}

				}

			}

		}
	}

	private boolean isDailyTransactionInTheMonth(Date transactionDate, Date fileGenerationDate) {
		Boolean transactionDateValid = false;
		if (null == fileGenerationDate) {
			transactionDateValid = true;
		} else if (transactionDate.compareTo(DateUtil.startDateTimeForDailyFromFileDate(fileGenerationDate)) > 0) {
			if (transactionDate.compareTo(DateUtil.endDateTimeForDailyFromFileDate(fileGenerationDate)) < 0) {
				transactionDateValid = true;
			}
		}
		return transactionDateValid;

	}

	private boolean isLoadSurveyTransactionInTheMonth(Date transactionDate, Date fileGenerationDate) {
		Boolean transactionDateValid = false;
		if (null == fileGenerationDate) {
			transactionDateValid = true;
		} else if (transactionDate.compareTo(DateUtil.startDateTimeForLoadSurveyFromFileDate(fileGenerationDate)) > 0) {
			if (transactionDate.compareTo(DateUtil.endDateTimeForLoadSurveyFromFileDate(fileGenerationDate)) < 0) {
				transactionDateValid = true;
			}
		}
		return transactionDateValid;

	}

	private String processTamperLogTable(CMRIFileDataModel cmriFileDataModel, File fileToRead, BufferedReader buf)
			throws IOException, ParseException {

		List<TamperLogTransaction> tamperLogList = new ArrayList<TamperLogTransaction>();
		FileMaster fileDetails = cmriFileDataModel.getFileMaster();

		String fileName = FilenameUtils.getBaseName(fileToRead.getName());

		Integer startRecordNoTamperLog = -1;
		// Record No. Date&Time Tamper Duration TamperCount Voltage_R Voltage_Y
		// Voltage_B Current_R Current_Y Current_B Imp Wh Exp Wh Power Factor Record
		// Status

		Date transactionDate=null;
		String recordStatus = null, tamperType = null, tamperDuration=null;
		BigDecimal voltageRed = null;
		BigDecimal voltageYellow = null;
		BigDecimal voltageBlue = null;
		BigDecimal currentRed = null;
		BigDecimal currentYellow = null;
		BigDecimal currentBlue = null;
		BigDecimal impWh = null;
		BigDecimal expWh = null;
		Integer recordNo=null;
		Long tamperCount=null;

		Integer indexOfTamperLogRecordNo = -1;
		Integer indexOfTamperLogDateTime = -1;
		Integer indexOfTamperLogRecordStatus = -1;

		Integer indexOfTamperLogType=-1, indexOfTamperLogDuration=-1, indexOfTamperLogCount=-1, indexOfTamperLogVoltageRed=-1,
				indexOfTamperLogVoltageYellow=-1, indexOfTamperLogVolageBlue=-1, indexOfTamperLogCurrentRed=-1,
				indexOfTamperLogCurrentYellow=-1, indexOfTamperLogCurrentBlue=-1, indexOfTamperLogImpWh=-1,
				indexOfTamperLogExpWh=-1, indexOfTamperLogPF = -1;
		while (true) {
			String tamperLogTableLineJustFetched = buf.readLine();

			String headerNames[];

			if (tamperLogTableLineJustFetched == null) {

				return tamperLogTableLineJustFetched;
			} else if (tamperLogTableLineJustFetched.length() == 0) {

				return tamperLogTableLineJustFetched;
			} else if (tamperLogTableLineJustFetched.contains("DAILY_SURVEY")) {
				return tamperLogTableLineJustFetched;

			} else if (tamperLogTableLineJustFetched.contains("StartRecord")) {
				String[] startLoadSurveyRecordData = tamperLogTableLineJustFetched.split("\t");
				startRecordNoTamperLog = Integer.parseInt(startLoadSurveyRecordData[1]);
			} else if (tamperLogTableLineJustFetched.contains("Tamper Type")) {
				headerNames = tamperLogTableLineJustFetched.split("\t");

				// Date&Time

				indexOfTamperLogRecordNo = Arrays.asList(headerNames).indexOf("Record No.");
				indexOfTamperLogDateTime = Arrays.asList(headerNames).indexOf("Date&Time");

				indexOfTamperLogType = Arrays.asList(headerNames).indexOf("Tamper Type");
				indexOfTamperLogDuration = Arrays.asList(headerNames).indexOf("Tamper Duration");

				indexOfTamperLogCount = Arrays.asList(headerNames).indexOf("TamperCount");

				indexOfTamperLogVoltageRed = Arrays.asList(headerNames).indexOf("Voltage_R");
				indexOfTamperLogVoltageYellow = Arrays.asList(headerNames).indexOf("Voltage_Y");
				indexOfTamperLogVolageBlue = Arrays.asList(headerNames).indexOf("Voltage_B");

				indexOfTamperLogCurrentRed = Arrays.asList(headerNames).indexOf("Current_R");
				indexOfTamperLogCurrentYellow = Arrays.asList(headerNames).indexOf("Current_Y");
				indexOfTamperLogCurrentBlue = Arrays.asList(headerNames).indexOf("Current_B");

				indexOfTamperLogImpWh = Arrays.asList(headerNames).indexOf("Imp Wh");
				indexOfTamperLogExpWh = Arrays.asList(headerNames).indexOf("Exp Wh");

				indexOfTamperLogPF = Arrays.asList(headerNames).indexOf("Power Factor");

				indexOfTamperLogRecordStatus = Arrays.asList(headerNames).indexOf("Record Status");
			}
			if (startRecordNoTamperLog >= 0 && indexOfTamperLogType > 0 && indexOfTamperLogDuration > 0) {

				fileDetails.setTamperLogNoStart(startRecordNoTamperLog);

				while (true) {
					String tamperLogRecordJustFetched = buf.readLine();

					if (tamperLogRecordJustFetched.length() == 0) {
						if (null == fileDetails.getTamperLogNoEnd()) {
							fileDetails.setTamperLogNoEnd(0);
						}

						fileDetails.setTamperLogCount(
								fileDetails.getTamperLogNoEnd() - fileDetails.getTamperLogNoStart() + 1);

						cmriFileDataModel.setTamperLogs(tamperLogList);
						// loadSurveyTransactionDao.save(loadSurveyList, getLoggedInUser());

						return tamperLogRecordJustFetched;
					} else {

						String[] tamperLogRecord = tamperLogRecordJustFetched.split("\t");
						if (StringUtils.isNumeric(tamperLogRecord[0]) && tamperLogRecord[0].length() > 0) {
							// q1varhTotal = new
							// BigDecimal(loadSurveyRecord[indexOfLoadSurveyQ1varhTotal].trim());
							//						
							//						Date transactionDate=null;
							//						String recordStatus = null, tamperType = null, tamperDuration=null;
							//						BigDecimal voltageRed = null;
							//						BigDecimal voltageYellow = null;
							//						BigDecimal voltageBlue = null;
							//						BigDecimal currentRed = null;
							//						BigDecimal currentYellow = null;
							//						BigDecimal currentBlue = null;
							//						BigDecimal impWh = null;
							//						BigDecimal expWh = null;
							//						Integer recordNo=null;
							//						Long tamperCount=null;

							transactionDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
									.parse(tamperLogRecord[indexOfTamperLogDateTime]);
							recordNo = Integer.parseInt(tamperLogRecord[indexOfTamperLogRecordNo]);
							tamperCount = Long.parseLong(tamperLogRecord[indexOfTamperLogCount]);

							voltageBlue = new BigDecimal(tamperLogRecord[indexOfTamperLogVolageBlue].trim());
							voltageRed = new BigDecimal(tamperLogRecord[indexOfTamperLogVoltageRed].trim());
							voltageYellow = new BigDecimal(tamperLogRecord[indexOfTamperLogVoltageYellow].trim());

							currentBlue = new BigDecimal(tamperLogRecord[indexOfTamperLogCurrentBlue].trim());
							currentRed = new BigDecimal(tamperLogRecord[indexOfTamperLogCurrentRed].trim());
							currentYellow = new BigDecimal(tamperLogRecord[indexOfTamperLogCurrentYellow].trim());

							impWh = new BigDecimal(tamperLogRecord[indexOfTamperLogImpWh].trim());
							expWh= new BigDecimal(tamperLogRecord[indexOfTamperLogExpWh].trim());




							recordStatus = tamperLogRecord[indexOfTamperLogRecordStatus].trim();
							tamperType = tamperLogRecord[indexOfTamperLogType].trim();
							tamperDuration=tamperLogRecord[indexOfTamperLogDuration].trim();




							TamperLogTransaction tamperLogTransaction = new TamperLogTransaction(fileDetails.getLocation(),
									fileName, voltageRed, voltageYellow, voltageBlue, currentRed, currentYellow,
									currentBlue, impWh, expWh, recordNo, recordStatus, tamperType, tamperDuration,
									tamperCount, transactionDate);

							if (tamperLogTransaction != null) {
								if (isLoadSurveyTransactionInTheMonth(tamperLogTransaction.getTransactionDate(),
										fileDetails.getTransactionDate())) {
									tamperLogList.add(tamperLogTransaction);
								}
							}
						}
					}

				}

			}

		}
	}

	@Async("threadPoolTaskExecutor")
	private LoadSurveyTransaction processLoadSurveyRow(String fileName, FileMaster fileDetails,
			Integer indexOfLoadSurveyRecordNo, Integer indexOfLoadSurveyDateTime,
			Integer indexOfLoadSurveyImportWhFundTotal, Integer indexOfLoadSurveyExportWhFundTotal,
			Integer indexOfLoadSurveyAvgFrequency, Integer indexOfLoadSurveyQ1varhTotal,
			Integer indexOfLoadSurveyQ2varhTotal, Integer indexOfLoadSurveyQ3varhTotal,
			Integer indexOfLoadSurveyQ4varhTotal, Integer indexOfLoadSurveyNetWh, Integer indexOfLoadSurveyFreqcode,
			Integer indexOfLoadSurveyImportVAhTotal, Integer indexOfLoadSurveyExportVAhTotal,
			Integer indexOfLoadSurveyImportWhTotal, Integer indexOfLoadSurveyExportWhTotal,
			Integer indexOfLoadSurveyStatusIndication, Integer indexOfLoadSurveyRecordStatus, String[] loadSurveyRecord)
					throws ParseException {
		if (StringUtils.isNumeric(loadSurveyRecord[0]) && loadSurveyRecord[0].length() > 0) {
			Date transactionDate = null;
			Integer recordNo = null;
			BigDecimal importWhFundTotal = null;
			BigDecimal exportWhFundTotal = null;
			BigDecimal avgFrequency = null;
			BigDecimal q1varhTotal = null;
			BigDecimal q2varhTotal = null;
			BigDecimal q3varhTotal = null;
			BigDecimal q4varhTotal = null;
			BigDecimal netWh = null;
			BigDecimal freqcode = null;
			BigDecimal importVAhTotal = null;
			BigDecimal exportVAhTotal = null;
			BigDecimal importWhTotal = null;
			BigDecimal exportWhTotal = null;
			BigDecimal statusIndication = null;
			String recordStatus = null;

			try {
				transactionDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
						.parse(loadSurveyRecord[indexOfLoadSurveyDateTime]);
				recordNo = Integer.parseInt(loadSurveyRecord[indexOfLoadSurveyRecordNo]);
				importWhFundTotal = new BigDecimal(loadSurveyRecord[indexOfLoadSurveyImportWhFundTotal].trim());
				exportWhFundTotal = new BigDecimal(loadSurveyRecord[indexOfLoadSurveyExportWhFundTotal].trim());
				avgFrequency = new BigDecimal(loadSurveyRecord[indexOfLoadSurveyAvgFrequency].trim());
				q1varhTotal = new BigDecimal(loadSurveyRecord[indexOfLoadSurveyQ1varhTotal].trim());
				q2varhTotal = new BigDecimal(loadSurveyRecord[indexOfLoadSurveyQ2varhTotal].trim());
				q3varhTotal = new BigDecimal(loadSurveyRecord[indexOfLoadSurveyQ3varhTotal].trim());
				q4varhTotal = new BigDecimal(loadSurveyRecord[indexOfLoadSurveyQ4varhTotal].trim());
				netWh = new BigDecimal(loadSurveyRecord[indexOfLoadSurveyNetWh].trim());

				importVAhTotal = new BigDecimal(loadSurveyRecord[indexOfLoadSurveyImportVAhTotal].trim());
				exportVAhTotal = new BigDecimal(loadSurveyRecord[indexOfLoadSurveyExportVAhTotal].trim());
				importWhTotal = new BigDecimal(loadSurveyRecord[indexOfLoadSurveyImportWhTotal].trim());
				exportWhTotal = new BigDecimal(loadSurveyRecord[indexOfLoadSurveyExportWhTotal].trim());
				statusIndication = new BigDecimal(loadSurveyRecord[indexOfLoadSurveyStatusIndication].trim());
				recordStatus = loadSurveyRecord[indexOfLoadSurveyRecordStatus];

				fileDetails.setSurveyRecordNoEnd(recordNo);

				return new LoadSurveyTransaction(fileName, fileDetails.getLocation(), transactionDate, recordNo,
						importWhFundTotal, exportWhFundTotal, avgFrequency, q1varhTotal, q2varhTotal, q3varhTotal,
						q4varhTotal, netWh, freqcode, importVAhTotal, exportVAhTotal, importWhTotal, exportWhTotal,
						statusIndication, recordStatus);
			} catch (Exception e) {
				// System.out.println(loadSurveyRecord);

				fileDetails.setSurveyRecordNoEnd(recordNo);
				e.printStackTrace();
				System.out.println(e.getMessage());
				return new LoadSurveyTransaction(fileName, fileDetails.getLocation(), transactionDate, recordNo,
						importWhFundTotal, exportWhFundTotal, avgFrequency, q1varhTotal, q2varhTotal, q3varhTotal,
						q4varhTotal, netWh, freqcode, importVAhTotal, exportVAhTotal, importWhTotal, exportWhTotal,
						statusIndication, recordStatus);
			}
		}
		return null;
	}

	private String processLoadSurveyTable(CMRIFileDataModel cmriFileDataModel, File fileToRead, BufferedReader buf)
			throws IOException, ParseException {

		List<LoadSurveyTransaction> loadSurveyList = new ArrayList<LoadSurveyTransaction>();
		FileMaster fileDetails = cmriFileDataModel.getFileMaster();

		String fileName = FilenameUtils.getBaseName(fileToRead.getName());
		Integer startRecordNoLoadSurvey = -1;
		Integer indexOfLoadSurveyRecordNo = -1;
		Integer indexOfLoadSurveyDateTime = -1;
		Integer indexOfLoadSurveyImportWhFundTotal = -1;
		Integer indexOfLoadSurveyExportWhFundTotal = -1;
		Integer indexOfLoadSurveyAvgFrequency = -1;
		Integer indexOfLoadSurveyQ1varhTotal = -1;
		Integer indexOfLoadSurveyQ2varhTotal = -1;
		Integer indexOfLoadSurveyQ3varhTotal = -1;
		Integer indexOfLoadSurveyQ4varhTotal = -1;
		Integer indexOfLoadSurveyNetWh = -1;
		Integer indexOfLoadSurveyFreqcode = -1;
		Integer indexOfLoadSurveyImportVAhTotal = -1;
		Integer indexOfLoadSurveyExportVAhTotal = -1;
		Integer indexOfLoadSurveyImportWhTotal = -1;
		Integer indexOfLoadSurveyExportWhTotal = -1;
		Integer indexOfLoadSurveyStatusIndication = -1;
		Integer indexOfLoadSurveyRecordStatus = -1;

		while (true) {
			String loadSurveylineJustFetched = buf.readLine();

			String headerNames[];

			if (loadSurveylineJustFetched == null) {

				return loadSurveylineJustFetched;
			} else if (loadSurveylineJustFetched.length() == 0) {

				return loadSurveylineJustFetched;
			} else if (loadSurveylineJustFetched.contains("LOAD_SURVEY2")) {
				return loadSurveylineJustFetched;

			} else if (loadSurveylineJustFetched.contains("StartRecord")) {
				String[] startLoadSurveyRecordData = loadSurveylineJustFetched.split("\t");
				startRecordNoLoadSurvey = Integer.parseInt(startLoadSurveyRecordData[1]);
			} else if (loadSurveylineJustFetched.contains("Import Wh (Fund) Total")) {
				headerNames = loadSurveylineJustFetched.split("\t");
				indexOfLoadSurveyRecordNo = Arrays.asList(headerNames).indexOf("Record No.");
				indexOfLoadSurveyDateTime = Arrays.asList(headerNames).indexOf("Date/Time");
				indexOfLoadSurveyImportWhFundTotal = Arrays.asList(headerNames).indexOf("Import Wh (Fund) Total");
				indexOfLoadSurveyExportWhFundTotal = Arrays.asList(headerNames).indexOf("Export Wh (Fund) Total");
				indexOfLoadSurveyAvgFrequency = Arrays.asList(headerNames).indexOf("Avg Frequency");
				indexOfLoadSurveyQ1varhTotal = Arrays.asList(headerNames).indexOf("Q1 varh Total");
				indexOfLoadSurveyQ2varhTotal = Arrays.asList(headerNames).indexOf("Q2 varh Total");
				indexOfLoadSurveyQ3varhTotal = Arrays.asList(headerNames).indexOf("Q3 varh Total");
				indexOfLoadSurveyQ4varhTotal = Arrays.asList(headerNames).indexOf("Q4 varh Total");
				indexOfLoadSurveyNetWh = Arrays.asList(headerNames).indexOf("Net Wh");
				indexOfLoadSurveyFreqcode = Arrays.asList(headerNames).indexOf("Freqcode");
				indexOfLoadSurveyImportVAhTotal = Arrays.asList(headerNames).indexOf("Import VAh Total");
				indexOfLoadSurveyExportVAhTotal = Arrays.asList(headerNames).indexOf("Export VAh Total");
				indexOfLoadSurveyImportWhTotal = Arrays.asList(headerNames).indexOf("Import Wh Total");
				indexOfLoadSurveyExportWhTotal = Arrays.asList(headerNames).indexOf("Export Wh Total");
				indexOfLoadSurveyStatusIndication = Arrays.asList(headerNames).indexOf("Status Indication");
				indexOfLoadSurveyRecordStatus = Arrays.asList(headerNames).indexOf("Record Status");
			}
			if (startRecordNoLoadSurvey >= 0 && indexOfLoadSurveyImportWhFundTotal > 0
					&& indexOfLoadSurveyExportWhFundTotal > 0) {

				fileDetails.setSurveyRecordNoStart(startRecordNoLoadSurvey);

				while (true) {
					String loadSurveyRecordJustFetched = buf.readLine();

					if (loadSurveyRecordJustFetched.length() == 0) {
						if (null == fileDetails.getSurveyRecordNoEnd()) {
							fileDetails.setSurveyRecordNoEnd(0);
						}

						fileDetails.setSurveyRecordCount(
								fileDetails.getSurveyRecordNoEnd() - fileDetails.getSurveyRecordNoStart() + 1);

						cmriFileDataModel.setLoadSurveyTransactions(loadSurveyList);
						// loadSurveyTransactionDao.save(loadSurveyList, getLoggedInUser());

						return loadSurveyRecordJustFetched;
					} else {

						String[] loadSurveyRecord = loadSurveyRecordJustFetched.split("\t");

						LoadSurveyTransaction loadSurveyTransaction = processLoadSurveyRow(fileName, fileDetails,
								indexOfLoadSurveyRecordNo, indexOfLoadSurveyDateTime,
								indexOfLoadSurveyImportWhFundTotal, indexOfLoadSurveyExportWhFundTotal,
								indexOfLoadSurveyAvgFrequency, indexOfLoadSurveyQ1varhTotal,
								indexOfLoadSurveyQ2varhTotal, indexOfLoadSurveyQ3varhTotal,
								indexOfLoadSurveyQ4varhTotal, indexOfLoadSurveyNetWh, indexOfLoadSurveyFreqcode,
								indexOfLoadSurveyImportVAhTotal, indexOfLoadSurveyExportVAhTotal,
								indexOfLoadSurveyImportWhTotal, indexOfLoadSurveyExportWhTotal,
								indexOfLoadSurveyStatusIndication, indexOfLoadSurveyRecordStatus, loadSurveyRecord);
						if (loadSurveyTransaction != null) {
							if (isLoadSurveyTransactionInTheMonth(loadSurveyTransaction.getTransactionDate(),
									fileDetails.getTransactionDate())) {
								loadSurveyList.add(loadSurveyTransaction);
							}
						}
					}

				}

			}

		}
	}

	private String processMeterInfo(FileMaster fileDetails, BufferedReader buf) throws IOException {
		while (true) {
			String meterInfolineJustFetched = buf.readLine();
			if (meterInfolineJustFetched == null) {

				return meterInfolineJustFetched;
			} else if (meterInfolineJustFetched.contains("PlantNumber")) {

				try {
					String[] meterNoData = meterInfolineJustFetched.split("\t");
					String meterNo = meterNoData[1];
					if (!meterNo.equalsIgnoreCase(fileDetails.getMeterMaster().getMeterSrNo())) {
						fileDetails.setProcessingStatus(EAUtil.FILE_ERROR_METER_NO_MISMATCH);
					}
				} catch (Exception e) {
					if (e.getClass().equals(ArrayIndexOutOfBoundsException.class)) {
						fileDetails.setProcessingStatus(EAUtil.FILE_ERROR_METER_NO_NOT_MENTIONED);
					}
				}
				return meterInfolineJustFetched;
			}
		}
	}

	private FileMaster processPlantInfo(FileMaster fileDetails, String lineJustFetched) {
		String meterNoAtTop;
		String[] meterNoData = lineJustFetched.split("\t");
		try {
			meterNoAtTop = meterNoData[1];
			if (meterNoAtTop != null) {

				fileDetails.setMeterMaster(meterDao.findByMeterSrNo(meterNoAtTop));
				if (null == fileDetails.getMeterMaster()) {
					fileDetails.setProcessingStatus(EAUtil.FILE_ERROR_METER_NOT_FOUND);
				} else {
					fileDetails.setLocation(fileDetails.getMeterMaster().getLocationMaster());

				}
			}
		} catch (Exception e) {
			if (e.getClass().equals(ArrayIndexOutOfBoundsException.class)) {
				fileDetails.setProcessingStatus(EAUtil.FILE_ERROR_METER_NO_NOT_MENTIONED);
			}
		}
		return fileDetails;
	}

	public CMRIFileDataModel extractTransactionDataFromFile(CMRIFileDataModel cmriFileDataModel) {

		FileMaster fileDetails = cmriFileDataModel.getFileMaster();
		if ((fileDetails.getProcessingStatus().equals(EAUtil.FILE_ZIP_EXTRACTED)
				|| fileDetails.getProcessingStatus().equals(EAUtil.FILE_TXT_PROCESSED))) {

			if (null != fileDetails.getTxtfileName()) {

				File fileToRead = new File(fileDetails.getTxtfileName());
				fileDetails.setDailyRecordCount(-1);
				fileDetails.setSurveyRecordCount(-1);
				try {

					BufferedReader buf = new BufferedReader(new FileReader(fileToRead));

					String lineJustFetched = null;

					while (true) {
						lineJustFetched = buf.readLine();

						if (lineJustFetched == null) {

							if (fileDetails.getDailyRecordCount() >= 0 && fileDetails.getSurveyRecordCount() >= 0) {

								fileDetails.setProcessingStatus(EAUtil.FILE_TXT_PROCESSED);
							}

							break;
						} else {

							if (lineJustFetched.contains("PlantNumber")) {

								fileDetails = processPlantInfo(fileDetails, lineJustFetched);
							} else if (lineJustFetched.contains("Date/Time") && !lineJustFetched.contains("Record")) {

								String[] dateTimeData = lineJustFetched.split("\t");
								// Date/Time 0x0000F03D 01/10/18 13:32:27
								SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
								// output format: yyyy-MM-dd
								// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

								fileDetails.setTransactionDate(parser.parse(dateTimeData[2]));
								// fileDetails=fileMasterDao.findByLocationDateCombo(fileDetails.getLocation(),
								// fileDetails.getTransactionDate());

							} else if (lineJustFetched.contains("METER_INFO")) {

								lineJustFetched = processMeterInfo(fileDetails, buf);

							}

							else if (lineJustFetched.contains("LOAD_SURVEY")
									&& !lineJustFetched.contains("LOAD_SURVEY2")) {
								lineJustFetched = processLoadSurveyTable(cmriFileDataModel, fileToRead, buf);

							} else if (lineJustFetched.contains("TAMPER_LOG")) {
								// []

								lineJustFetched = processTamperLogTable(cmriFileDataModel, fileToRead, buf);

								// System.out.println(lineJustFetched);
							}

							else if (lineJustFetched.contains("DAILY_SURVEY")) {
								lineJustFetched = processDailySurveyTable(cmriFileDataModel, fileToRead, buf);

							}else if (lineJustFetched.contains("INSTANT_REGISTERS")) {

								lineJustFetched = processInstantRegistersTable(cmriFileDataModel, fileToRead, buf);
							}

						}
					}
					buf.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			else {
				fileDetails.setProcessingStatus(EAUtil.FILE_ERROR);
				return cmriFileDataModel;
			}
		}
		return cmriFileDataModel;
	}

	// @org.springframework.transaction.annotation.Transactional(value =
	// "sldcTxnManager")
	// public DailyTransaction saveDailyTransaction(DailyTransaction
	// dailyTransaction) {
	// DailyTransaction entity =
	// dailyTransactionDao.findByLocationDateCombo(dailyTransaction.getLocation(),
	// dailyTransaction.getTransactionDate());
	// if (null != entity) {
	// //
	// if(null==dailyTransaction.getTransactionStatus()||dailyTransaction.getTransactionStatus().equals(EAUtil.DAILY_TRANSACTION_NOT_ENTERED_YET)||dailyTransaction.getTransactionStatus().equals(EAUtil.DAILY_TRANSACTION_JE_SAVED_APPROVAL_PENDING))
	// {
	// // transaction read from file is auto approved
	// dailyTransaction.setTransactionStatus(EAUtil.DAILY_TRANSACTION_APPROVED_AE);
	// entity.updateValues(dailyTransaction, entity.getLocation().getMeterMaster());
	// dailyTransactionDao.update(entity, getLoggedInUser());
	// }
	// } else {
	// LocationMaster locationMaster =
	// locationMasterDao.findById(dailyTransaction.getLocation().getLocationId());
	// dailyTransaction.calculateEnergy(dailyTransaction,
	// locationMaster.getMeterMaster());
	// dailyTransaction.setTransactionStatus(EAUtil.DAILY_TRANSACTION_APPROVED_AE);
	// dailyTransactionDao.save(dailyTransaction, getLoggedInUser());
	//
	// }
	// return entity;
	// }
	//

	@org.springframework.transaction.annotation.Transactional(value = "sldcTxnManager")
	public void saveFileDetails(FileMaster fileDetails) {

		if (null != fileDetails.getMeterMaster()) {
			FileMaster entity = fileMasterDao.findByLocationDateCombo(fileDetails.getLocation(),
					fileDetails.getTransactionDate());
			if (null != entity) {
				{

					if (null != fileDetails.getZipfileName()) {
						// In case file from same location and generated at same time is uploaded by the
						// user again but when the user has already changed the zip files name
						// system will keep the old file in zip repository but the Database will contain
						// the record for latest updated file only and thus uploaded by will change
						// accordingly
						if (!fileDetails.getZipfileName().equalsIgnoreCase(entity.getZipfileName())) {
							entity.setUploadedBy(getLoggedInUser());
						}
					}
					if (null != fileDetails.getTxtfileName()) {
						if (!fileDetails.getTxtfileName().equalsIgnoreCase(entity.getTxtfileName())) {
							entity.setUploadedBy(getLoggedInUser());
						}
					}
					entity.updateValues(fileDetails);
					fileMasterDao.update(entity, getLoggedInUser());
				}
			} else {
				fileMasterDao.save(fileDetails, getLoggedInUser());

			}
		} else {
			fileMasterDao.save(fileDetails, getLoggedInUser());

		}
	}

	// private FileService fileService;

	public void setTextFile(FileMaster textFile) {
		this.textFile = textFile;
	}

	public void saveLoadDailyFileDataToDB(CMRIFileDataModel cmriFileDataModel) {
		if (cmriFileDataModel.getFileMaster().getFileActionStatus().equals(EAUtil.FILE_ACTION__APPROVED_AE)
				&& null != cmriFileDataModel.getFileMaster().getLocation()) {
			dailyTransactionDao.save(cmriFileDataModel.getDailyTransactions(), getLoggedInUser());
			loadSurveyTransactionDao.save(cmriFileDataModel.getLoadSurveyTransactions(), getLoggedInUser());
			saveFileDetails(cmriFileDataModel.getFileMaster());
			tamperLogDao.save(cmriFileDataModel.getTamperLogs(), getLoggedInUser());
			
		} else {
			saveFileDetails(cmriFileDataModel.getFileMaster());
			// fileMasterDao.update(cmriFileDataModel.getFileMaster(), getLoggedInUser());
		}
	}

	public void saveTamperData(CMRIFileDataModel cmriFileDataModel) {
		if (cmriFileDataModel.getFileMaster().getFileActionStatus().equals(EAUtil.FILE_ACTION__APPROVED_AE)
				&& null != cmriFileDataModel.getFileMaster().getLocation()) {
			// dailyTransactionDao.save(cmriFileDataModel.getDailyTransactions(),
			// getLoggedInUser());
			// loadSurveyTransactionDao.save(cmriFileDataModel.getLoadSurveyTransactions(),
			// getLoggedInUser());
			tamperLogDao.save(cmriFileDataModel.getTamperLogs(), getLoggedInUser());

			saveFileDetails(cmriFileDataModel.getFileMaster());

		} else {
			saveFileDetails(cmriFileDataModel.getFileMaster());
			// fileMasterDao.update(cmriFileDataModel.getFileMaster(), getLoggedInUser());
		}
	}

	/*
	 * @Async("threadPoolTaskExecutor")
	 * 
	 * @org.springframework.transaction.annotation.Transactional(value =
	 * "sldcTxnManager") public void saveLoadSurveyTransaction(LoadSurveyTransaction
	 * loadSurveyTransaction) { LoadSurveyTransaction entity =
	 * loadSurveyTransactionDao.findByLocationDateCombo(
	 * loadSurveyTransaction.getLocation(),
	 * loadSurveyTransaction.getTransactionDate()); if (null != entity) { //
	 * if(null==dailyTransaction.getTransactionStatus()||dailyTransaction.
	 * getTransactionStatus().equals(EAUtil.DAILY_TRANSACTION_NOT_ENTERED_YET)||
	 * dailyTransaction.getTransactionStatus().equals(EAUtil.
	 * DAILY_TRANSACTION_JE_SAVED_APPROVAL_PENDING)) { // transaction read from file
	 * is auto approved entity.updateValues(loadSurveyTransaction,
	 * entity.getLocation().getMeterMaster());
	 * loadSurveyTransactionDao.update(entity, getLoggedInUser()); } } else {
	 * LocationMaster locationMaster = locationMasterDao
	 * .findById(loadSurveyTransaction.getLocation().getLocationId());
	 * loadSurveyTransactionDao.save(loadSurveyTransaction, getLoggedInUser());
	 * 
	 * } }
	 */

}
