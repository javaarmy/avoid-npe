package com.lifetimefitness.workday.common.utilities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import workday.com.bsvc.Balance_Period_Detail_DataType;
import workday.com.bsvc.CompanyObjectIDType;
import workday.com.bsvc.CompanyObjectType;
import workday.com.bsvc.Cost_CenterObjectIDType;
import workday.com.bsvc.Cost_CenterObjectType;
import workday.com.bsvc.Deduction_ReferenceType;
import workday.com.bsvc.Earning_ReferenceType;
import workday.com.bsvc.EmployeeObjectIDType;
import workday.com.bsvc.Last_Name_DataType;
import workday.com.bsvc.Name_DataType;
import workday.com.bsvc.National_ID_DataType;
import workday.com.bsvc.National_ID_TypeObjectIDType;
import workday.com.bsvc.Pay_Calculation_Balance_DataType;
import workday.com.bsvc.Pay_Component_ReferenceType;
import workday.com.bsvc.Payroll_BalanceType;
import workday.com.bsvc.Payroll_ResultType;
import workday.com.bsvc.Payroll_Worktag_DataType;
import workday.com.bsvc.RegionObjectIDType;
import workday.com.bsvc.RegionObjectType;
import workday.com.bsvc.Related_Calculation_Data_For_Get_Payroll_ResultsType;
import workday.com.bsvc.Result_Line_Details_For_Get_Payroll_ResultsType;
import workday.com.bsvc.WorkerObjectIDType;
import workday.com.bsvc.Balance_Period_Data_For_Get_Payroll_BalancesType;

import com.lifetimefitness.batch.utilities.AbstractTranslator;
import com.lifetimefitness.employeesync.common.constants.EmployeeSyncConstants;
import com.lifetimefitness.workday.common.constants.WorkdayConstants;

/*******************************************************************************
 * Base class for Workday Payroll translation.
 * 
 * @param <I> generic typed parameter for input object
 * @param <O> generic typed parameter for output object
 */
public
abstract
class WorkdayPayrollTranslator< I, O >
   extends AbstractTranslator< I, O >
{

   private  static
   final    String      WORKDAY_SSN                            = "USA-SSN";
   
/*******************************************************************************
 * Constructor
 */
   public
   WorkdayPayrollTranslator()
   {
      super();
   }
   
/******************************************************************************
 * Gets the payment date from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the payment date, or null if not found
 */
   public
   Calendar
   getPaymentDate( Payroll_ResultType a_result )
   {
      if( a_result != null )
      {
         return a_result.getPayment_Date();
      }

      return null;
   }
   
/******************************************************************************
 * Gets the payroll period start date from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the payroll period start date, or null if not found
 */
   public
   Calendar
   getPayrollPeriodStartDate( Payroll_ResultType a_result )
   {
      if( a_result != null )
      {
         return a_result.getPeriod_Start_Date();
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the payroll period end date from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the payroll period end date, or null if not found
 */
   public
   Calendar
   getPayrollPeriodEndDate( Payroll_ResultType a_result )
   {
      if( a_result != null )
      {
         return a_result.getPeriod_End_Date();
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the payroll run category from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the payroll run category, or null if not found
 */
   public
   String
   getPayrollRunCategory( Payroll_ResultType a_result )
   {
      if( a_result != null
             &&
          a_result.getRun_Category_Reference() != null )
      {
         return a_result.getRun_Category_Reference()
                        .getDescriptor();
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the payroll net amount from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the payroll net amount, or null if not found
 */
   public
   BigDecimal
   getNetAmount( Payroll_ResultType a_result )
   {
      if( a_result != null )
      {
         return a_result.getNet_Amount();
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the payroll gross amount from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the payroll gross amount, or null if not found
 */
   public
   BigDecimal
   getGrossAmount( Payroll_ResultType a_result )
   {
      if( a_result != null )
      {
         return a_result.getGross_Amount();
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the worker's employee id from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the employee id, or null if not found
 */
   public
   String
   getEmployeeId( Payroll_ResultType a_result )
   {
      if( a_result != null
            &&
          a_result.getEmployee_Reference() != null )
      {
         for( EmployeeObjectIDType l_id
                                 : a_result.getEmployee_Reference().getID() )
         {
            if( l_id != null
                   &&
                EmployeeSyncConstants.WORKDAY_ENUM_EMPLOYEE_ID
                                     .equals( l_id.getType() ) )
            {
               return l_id.get_value();
            }
         }
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the worker's Social Security Number from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the SSN, or null if not found
 */
   public
   String
   getSocialSecurityNumber( Payroll_ResultType a_result )
   {
      if( a_result != null
             &&
          a_result.getNational_ID_Data() != null )
      {
         for( National_ID_DataType l_id
                  : a_result.getNational_ID_Data().getNational_ID_Data() )
         {
            if( l_id != null )
            {
               for( National_ID_TypeObjectIDType l_id_type
                       : l_id.getID_Type_Reference().getID() )
               {
                  if( l_id_type != null
                         &&
                      EmployeeSyncConstants.WORKDAY_ENUM_NATIONAL_ID_TYPE_CODE
                                           .equals( l_id_type.getType() )
                         &&
                      WORKDAY_SSN.equals( l_id_type.get_value() ) )
                  {
                     return l_id.getID();
                  }
               }
            }
         }
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the worker's first name from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the first name, or null if not found
 */
   public
   String
   getFirstName( Payroll_ResultType a_result )
   {
      if( a_result != null
             &&
          a_result.getEmployee_Name_Data() != null )
      {
         for( Name_DataType l_name : a_result.getEmployee_Name_Data()
                                                               .getName_Data() )
         {
            if( l_name != null
                   &&
                l_name.getIs_Preferred() )
            {
               return l_name.getFirst_Name();
            }
         }
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the worker's middle name from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the middle name, or null if not found
 */
   public
   String
   getMiddleName( Payroll_ResultType a_result )
   {
      if( a_result != null
             &&
          a_result.getEmployee_Name_Data() != null )
      {
         for( Name_DataType l_name : a_result.getEmployee_Name_Data()
                                                               .getName_Data() )
         {
            if( l_name != null
                   &&
                l_name.getIs_Preferred() )
            {
               return l_name.getMiddle_Name();
            }
         }
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the worker's last name from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the last name, or null if not found
 */
   public
   String
   getLastName( Payroll_ResultType a_result )
   {
      if( a_result != null
             &&
          a_result.getEmployee_Name_Data() != null )
      {
         for( Name_DataType l_name : a_result.getEmployee_Name_Data()
                                                               .getName_Data() )
         {
            if( l_name != null
                   &&
                l_name.getLast_Name() != null
                   &&
                l_name.getIs_Preferred() )
            {
               for( Last_Name_DataType l_last_name_type
                                                   : l_name.getLast_Name() )
               {
                  if( l_last_name_type != null
                         &&
                      "Primary".equals( l_last_name_type.getType() ) )
                  {
                     return l_last_name_type.get_value();
                  }
               }
            }
         }
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the worker's name suffix from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the name suffix, or null if not found
 */
   public
   String
   getNameSuffix( Payroll_ResultType a_result )
   {
      if( a_result != null
             &&
          a_result.getEmployee_Name_Data() != null )
      {
         for( Name_DataType l_name : a_result.getEmployee_Name_Data()
                                                               .getName_Data() )
         {
            if( l_name != null
                   &&
                l_name.getIs_Preferred() )
            {
               if( l_name.getSuffix() != null
                     &&
                   l_name.getSuffix().length > 0 )
               {
                  return l_name.getSuffix()[ 0 ].get_value();
               }
               return null;
            }
         }
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the amount from the Payroll_ResultType object for the deduction.
 * 
 * @param  a_result
 * @return the deduction amount, or null if not found
 */
   public
   BigDecimal
   getDeductionAmount( Payroll_ResultType a_result,
                       String             a_deduction_code )
   {
      Deduction_ReferenceType    l_deduction = null;
      BigDecimal                 l_amount    =   null;
      
      if( a_result != null
            &&
          a_result.getPayroll_Result_Data() != null )
      {
         for( Result_Line_Details_For_Get_Payroll_ResultsType l_details
                 : a_result.getPayroll_Result_Data() )
         {
            if( l_details != null
                  &&
                l_details.getPay_Component_Reference() != null )
            {
               for( Pay_Component_ReferenceType l_ref
                       : l_details.getPay_Component_Reference() )
               {
                  if( l_ref != null )
                  {
                     l_deduction = l_ref.getDeduction();
                     if( l_deduction != null
                           &&
                         a_deduction_code.equals( l_deduction.getCode() ) )
                     {
                        if( l_amount == null )
                        {
                           l_amount = BigDecimal.ZERO;
                        }
                        l_amount = l_amount.add( l_details
                                                    .getResult_Line_Amount() );
                     }
                  }
               }
            }
         }
      }
      return l_amount;
   }
   
/******************************************************************************
 * Returns a list of all clubs worked at by the employee.
 * 
 * @param  a_payroll_results
 * @return a list of all clubs worked at by the employee
 */
   public
   List<String>
   getClubsWorked( Payroll_ResultType[] a_payroll_results )
   {
      List<String> l_clubs = new ArrayList<String>();
      String       l_hr_club_id;
      
      if( a_payroll_results != null )
      {
         for( Payroll_ResultType l_payroll_result : a_payroll_results )
         {
            l_hr_club_id = getClubId( l_payroll_result );
            if( !l_clubs.contains( l_hr_club_id ) )
            {
               l_clubs.add( l_hr_club_id );
            }
         }
      }
      
      return l_clubs;
   }
   
/******************************************************************************
 * Gets the hours worked amount from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the hours worked amount, or null if not found
 */
   public
   BigDecimal
   getHoursWorked( Payroll_ResultType a_result )
   {
      BigDecimal  l_hours = new BigDecimal( 0 );
      String      l_calc_desc;
      
      if( a_result != null )
      {
         for( Result_Line_Details_For_Get_Payroll_ResultsType l_details
                 : a_result.getPayroll_Result_Data() )
         {
            if( l_details != null )
            {
               for( Pay_Component_ReferenceType l_ref
                     : l_details.getPay_Component_Reference() )
               {
                  if( l_ref != null
                        &&
                      l_ref.getEarning() != null
                         &&
                      WorkdayConstants.HOURLY_EARNINGS.contains(
                                              l_ref.getEarning().getCode() ) )
                  {
                     for( Related_Calculation_Data_For_Get_Payroll_ResultsType
                              l_calc
                              : l_details.getRelated_Calculation_Result_Data() )
                     {
                        if( l_calc != null
                               &&
                            l_calc.getRelated_Calculation_Reference() != null )
                        {
                           l_calc_desc
                              = l_calc.getRelated_Calculation_Reference()
                                      .getDescriptor();
                           if( l_calc_desc.equals(
                                    WorkdayConstants.PAY_CALC_REF_HOURS_WORKED )
                                    ||
                               l_calc_desc.equals(
                                    WorkdayConstants
                                       .PAY_CALC_REF_HOURS_WORKED_UNPRORATED ) )
                           {
                              l_hours
                                 = l_hours.add(
                                             l_calc.getRelated_Amount_Value() );
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      
      return l_hours;
   }
   
/******************************************************************************
 * Gets the hourly wage amount from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the hourly wage amount, or null if not found
 */
   public
   BigDecimal
   getHourlyWage( Payroll_ResultType a_result )
   {
      String l_earning;
      
      if( a_result != null )
      {
         for( Result_Line_Details_For_Get_Payroll_ResultsType l_details
                 : a_result.getPayroll_Result_Data() )
         {
            if( l_details != null )
            {
               for( Pay_Component_ReferenceType l_ref
                     : l_details.getPay_Component_Reference() )
               {
                  if( l_ref != null
                        &&
                      l_ref.getEarning() != null )
                  {
                     l_earning = l_ref.getEarning().getCode();
                     if( WorkdayConstants.HOURLY_EARNINGS.contains( l_earning )
                            &&
                         ! l_earning.equals(
                                  WorkdayConstants.EARNING_OVERTIME )
                            &&
                         ! l_earning.equals(
                                  WorkdayConstants.EARNING_DOUBLE_OVERTIME ) )
                     {
                        return l_details.getResult_Line_Amount();
                     }
                  }
               }
            }
         }
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the wages subject to FUTA (Federal Unemployment Tax Act) from the
 * Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the wages subject to FUTA, or null if not found
 */
   public
   BigDecimal
   getWagesSubjectToFUTA( Payroll_ResultType a_result )
   {
      BigDecimal l_wages = new BigDecimal( 0 );
      
      if( a_result != null )
      {
         for( Result_Line_Details_For_Get_Payroll_ResultsType l_details
                 : a_result.getPayroll_Result_Data() )
         {
            if( l_details != null )
            {
               for( Pay_Component_ReferenceType l_ref
                     : l_details.getPay_Component_Reference() )
               {
                  if( l_ref != null
                        &&
                      l_ref.getEarning() != null
                        &&
                      ! WorkdayConstants.EARNINGS_NOT_SUBJECT_TO_FUTA
                                        .contains(
                                                l_ref.getEarning().getCode() ) )
                  {
                     if( l_details.getResult_Line_Amount() != null )
                     {
                         l_wages
                            = l_wages.add( l_details.getResult_Line_Amount() );
                     }
                  }
               }
            }
         }
      }
      
      return l_wages;
   }
   
/******************************************************************************
 * Gets the Year-To-Date hours worked from the Payroll_ResultType object, for
 * the specified location.
 * 
 * @param  a_payroll_results
 * @param  a_work_site_id
 * @return the Year-To-Date hours worked
 */
   public
   BigDecimal
   getYTDHoursWorked( Payroll_ResultType[] a_payroll_results,
                      String               a_work_site_id )
   {
      BigDecimal l_ytd_hours_worked = new BigDecimal( 0 );
      
      if( a_payroll_results != null
             &&
          a_work_site_id != null )
      {
         for( Payroll_ResultType l_payroll_result : a_payroll_results )
         {
            if( a_work_site_id.equals( getClubId( l_payroll_result ) ) )
            {
               l_ytd_hours_worked
                 = l_ytd_hours_worked.add( getHoursWorked( l_payroll_result ) );
            }
         }
      }
      
      return l_ytd_hours_worked;
   }
   
/******************************************************************************
 * Gets the Year-To-Date pay (all types, gross) from the Payroll_ResultType
 * object, for the specified location.
 * 
 * @param  a_payroll_results
 * @param  a_club_id
 * @return the Year-To-Date gross pay (all types)
 */
   public
   BigDecimal
   getYTDAllPayGross( Payroll_ResultType[] a_payroll_results,
                      String               a_club_id )
   {
      BigDecimal l_all_pay_ytd_gross = new BigDecimal( 0 );
      
      if( a_payroll_results != null
             &&
          a_club_id != null )
      {
         for( Payroll_ResultType l_payroll_result : a_payroll_results )
         {
            if( a_club_id.equals( getClubId( l_payroll_result ) ) )
            {
               l_all_pay_ytd_gross
                  = l_all_pay_ytd_gross.add(
                           getGrossAmount( l_payroll_result ) );
            }
         }
      }
      
      return l_all_pay_ytd_gross;
   }
   
/******************************************************************************
 * Gets the Year-To-Date wages subject to Federal Unemployment Tax Act (FUTA)
 * from the Payroll_ResultType object, for the specified location.
 * 
 * @param  a_payroll_results
 * @param  a_club_id
 * @return the Year-To-Date hours worked
 */
   public
   BigDecimal
   getYTDWagesSubjectToFUTA( Payroll_ResultType[] a_payroll_results,
                             String               a_club_id )
   {
      BigDecimal l_ytd_wages_subject_to_futa = new BigDecimal( 0 );
      
      if( a_payroll_results != null
             &&
          a_club_id != null )
      {
         for( Payroll_ResultType l_payroll_result : a_payroll_results )
         {
            if( a_club_id.equals( getClubId( l_payroll_result ) ) )
            {
               l_ytd_wages_subject_to_futa
                  = l_ytd_wages_subject_to_futa.add(
                                    getWagesSubjectToFUTA( l_payroll_result ) );
            }
         }
      }
      
      return l_ytd_wages_subject_to_futa;
   }
   
/******************************************************************************
 * Gets the Year-To-Date Earned Income Credit (EIC) from the Payroll_ResultType
 * object, for the specified location.
 * 
 * @param  a_payroll_results
 * @param  a_club_id
 * @return the Year-To-Date Earned Income Credit
 */
   public
   BigDecimal
   getYTDEarnedIncomeCredit( Payroll_ResultType[] a_payroll_results,
                             String               a_club_id )
   {
      BigDecimal l_ytd_eic_amount = new BigDecimal( 0 );
      BigDecimal l_amount;
      
      if( a_payroll_results != null
             &&
          a_club_id != null )
      {
         for( Payroll_ResultType l_payroll_result : a_payroll_results )
         {
            if( a_club_id.equals( getClubId( l_payroll_result ) ) )
            {
               l_amount = getDeductionAmount( l_payroll_result,
                     WorkdayConstants.DEDUCTION_EARNED_INCOME_CREDIT );
               if( l_amount != null )
               {
                  l_ytd_eic_amount = l_ytd_eic_amount.add( l_amount );
               }
            }
         }
      }
      
      return l_ytd_eic_amount;
   }
   
/******************************************************************************
 * Gets the Year-To-Date tips amount from the Payroll_ResultType object, for
 * the specified location.
 * 
 * @param  a_payroll_results
 * @param  a_club_id
 * @return the Year-To-Date tips amount
 */
   public
   BigDecimal
   getYTDTips( Payroll_ResultType[] a_payroll_results,
               String               a_club_id )
   {
      BigDecimal l_ytd_tips = new BigDecimal( 0 );
      
      if( a_payroll_results != null
             &&
          a_club_id != null )
      {
         for( Payroll_ResultType l_payroll_result : a_payroll_results )
         {
            if( a_club_id.equals( getClubId( l_payroll_result ) ) )
            {
               l_ytd_tips = l_ytd_tips.add( getTips( l_payroll_result ) );
            }
         }
      }
      
      return l_ytd_tips;
   }
   
/******************************************************************************
 * Gets the tips amount from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the tips amount, or null if not found
 */
   public
   BigDecimal
   getTips( Payroll_ResultType a_result )
   {
      BigDecimal l_tips = new BigDecimal( 0 );
      
      if( a_result != null )
      {
         for( Result_Line_Details_For_Get_Payroll_ResultsType l_details
                 : a_result.getPayroll_Result_Data() )
         {
            if( l_details != null )
            {
               for( Pay_Component_ReferenceType l_ref
                     : l_details.getPay_Component_Reference() )
             {
                  if( l_ref != null
                        &&
                      l_ref.getEarning() != null
                        &&
                      WorkdayConstants.EARNINGS_TIPS.contains(
                                                l_ref.getEarning().getCode() ) )
                  {
                     if( l_details.getResult_Line_Amount() != null )
                     {
                        l_tips
                            = l_tips.add( l_details.getResult_Line_Amount() );
                     }
                  }
               }
            }
         }
      }
      
      return l_tips;
   }
   
   
/******************************************************************************
 * Gets the Club Id from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the club id, or null if not found
 */
   public
   String
   getClubId( Payroll_ResultType a_result )
   {
      Payroll_Worktag_DataType l_worktag;
      
      if( a_result != null )
      {
         for( Result_Line_Details_For_Get_Payroll_ResultsType l_details
               : a_result.getPayroll_Result_Data() )
         {
            if( l_details != null )
            {
               l_worktag = l_details.getPayroll_Worktag_Data();
               if( l_worktag != null )
               {
                  for( RegionObjectType l_region
                        : l_worktag.getRegion_Worktag_Reference() )
                  {
                     if( l_region != null )
                     {
                        for( RegionObjectIDType l_region_id : l_region.getID() )
                        {
                           if( EmployeeSyncConstants
                                  .WORKDAY_ENUM_ORGANIZATION_REF_ID
                                  .equals( l_region_id.getType() ) )
                           {
                              return l_region_id.get_value();
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the Company Reference from the Payroll_ResultType object for the
 * deduction.
 * 
 * @param  a_result
 * @return the company reference, or null if not found
 */
   public
   String
   getCompanyReferenceForDeduction( Payroll_ResultType a_result,
                                    String             a_deduction_code )
   {
      BigDecimal                 l_amount = null;
      Deduction_ReferenceType    l_deduction = null;
      Payroll_Worktag_DataType   l_payroll_worktag = null;
      
      if( a_result != null )
      {
         for( Result_Line_Details_For_Get_Payroll_ResultsType l_details
                 : a_result.getPayroll_Result_Data() )
         {
            if( l_details != null )
            {
               for( Pay_Component_ReferenceType l_ref
                       : l_details.getPay_Component_Reference() )
               {
                  if( l_ref != null )
                  {
                     l_deduction = l_ref.getDeduction();
                     if( l_deduction != null
                           &&
                           a_deduction_code.equals( l_deduction.getCode() ) )
                     {
                        l_amount = l_details.getResult_Line_Amount();
                     }
                  }
               }
               
               if( l_amount != null )
               {
                  l_payroll_worktag = l_details.getPayroll_Worktag_Data();
                  if( l_payroll_worktag != null )
                  {
                     for( CompanyObjectType l_company
                           : l_payroll_worktag.getCompany_Worktag_Reference() )
                     {
                        for( CompanyObjectIDType l_company_type
                                                         : l_company.getID() )
                        {
                           if( EmployeeSyncConstants
                                  .WORKDAY_ENUM_ORGANIZATION_REF_ID
                                  .equals( l_company_type.getType() ) )
                           {
                              return l_company_type.get_value();
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the Region Id from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the region id, or null if not found
 */
   public
   String
   getRegionId( Payroll_ResultType a_result )
   {
      Payroll_Worktag_DataType l_worktag;
      
      if( a_result != null )
      {
         for( Result_Line_Details_For_Get_Payroll_ResultsType l_details
               : a_result.getPayroll_Result_Data() )
         {
            if( l_details != null )
            {
               l_worktag = l_details.getPayroll_Worktag_Data();
               if( l_worktag != null )
               {
                  for( RegionObjectType l_region
                        : l_worktag.getRegion_Worktag_Reference() )
                  {
                     if( l_region != null )
                     {
                        for( RegionObjectIDType l_region_id : l_region.getID() )
                        {
                           if( EmployeeSyncConstants
                                  .WORKDAY_ENUM_ORGANIZATION_REF_ID
                                  .equals( l_region_id.getType() ) )
                           {
                              return l_region_id.get_value();
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the Cost Center from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the cost center, or null if not found
 */
   public
   String
   getCostCenter( Payroll_ResultType a_result )
   {
      Payroll_Worktag_DataType l_worktag;
      
      if( a_result != null )
      {
         for( Result_Line_Details_For_Get_Payroll_ResultsType l_details
               : a_result.getPayroll_Result_Data() )
         {
            if( l_details != null )
            {
               l_worktag = l_details.getPayroll_Worktag_Data();
               if( l_worktag != null )
               {
                  for( Cost_CenterObjectType l_cost_center
                         : l_worktag.getCost_Center_Worktag_Reference() )
                  {
                     if( l_cost_center != null )
                     {
                        for( Cost_CenterObjectIDType l_cost_center_id
                                  : l_cost_center.getID() )
                        {
                           if( EmployeeSyncConstants
                                  .WORKDAY_ENUM_ORGANIZATION_REF_ID
                                  .equals( l_cost_center_id.getType() ) )
                           {
                              return l_cost_center_id.get_value();
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the Cost Center from the Payroll_ResultType object for the deduction.
 * 
 * @param  a_result
 * @return the cost center, or null if not found
 */
   public
   String
   getCostCenterForDeduction( Payroll_ResultType a_result,
                              String             a_deduction_code )
   {
      BigDecimal                 l_amount = null;
      Deduction_ReferenceType    l_deduction = null;
      Payroll_Worktag_DataType   l_payroll_worktag = null;
      
      if( a_result != null )
      {
         for( Result_Line_Details_For_Get_Payroll_ResultsType l_details
                 : a_result.getPayroll_Result_Data() )
         {
            if( l_details != null )
            {
               for( Pay_Component_ReferenceType l_ref
                       : l_details.getPay_Component_Reference() )
               {
                  if( l_ref != null )
                  {
                     l_deduction = l_ref.getDeduction();
                     if( l_deduction != null
                           &&
                           a_deduction_code.equals( l_deduction.getCode() ) )
                     {
                        l_amount = l_details.getResult_Line_Amount();
                     }
                  }
               }
               
               if( l_amount != null )
               {
                  l_payroll_worktag = l_details.getPayroll_Worktag_Data();
                  for( Cost_CenterObjectType l_cost_center
                        : l_payroll_worktag
                                       .getCost_Center_Worktag_Reference() )
                  {
                     if( l_cost_center != null )
                     {
                        for( Cost_CenterObjectIDType l_cost_center_id
                                 : l_cost_center.getID() )
                        {
                           if( EmployeeSyncConstants
                                  .WORKDAY_ENUM_ORGANIZATION_REF_ID
                                  .equals( l_cost_center_id.getType() ) )
                           {
                              return l_cost_center_id.get_value();
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      
      return null;
   }
   
/******************************************************************************
 * Gets the amount of Regular Pay from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @param  a_earnings_map
 * @return the amount of Regular Pay, or zero if not found
 */
   public
   BigDecimal
   getRegularPay( Payroll_ResultType    a_result,
                  Map< String, String > a_earnings_map )
   {
      return getPayForCategory(
                    a_result,
                    a_earnings_map,
                    WorkdayConstants.EARNING_TYPE_REGULAR );
   }
   
/******************************************************************************
 * Gets the amount of Overtime Pay from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @param  a_earnings_map
 * @return the amount of Overtime Pay, or zero if not found
 */
   public
   BigDecimal
   getOvertimePay( Payroll_ResultType    a_result,
                   Map< String, String > a_earnings_map )
   {
      return getPayForCategory(
                    a_result,
                    a_earnings_map,
                    WorkdayConstants.EARNING_TYPE_OVERTIME );
   }
   
/******************************************************************************
 * Gets the amount of Vacation Pay from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @param  a_earnings_map
 * @return the amount of Vacation Pay, or zero if not found
 */
   public
   BigDecimal
   getVacationPay( Payroll_ResultType    a_result,
                   Map< String, String > a_earnings_map )
   {
      return getPayForCategory(
                    a_result,
                    a_earnings_map,
                    WorkdayConstants.EARNING_TYPE_VACATION );
   }
   
/******************************************************************************
 * Gets the amount of Holiday Pay from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @param  a_earnings_map
 * @return the amount of Holiday Pay, or zero if not found
 */
   public
   BigDecimal
   getHolidayPay( Payroll_ResultType    a_result,
                  Map< String, String > a_earnings_map )
   {
      return getPayForCategory(
                    a_result,
                    a_earnings_map,
                    WorkdayConstants.EARNING_TYPE_HOLIDAY );
   }
   
/******************************************************************************
 * Gets the amount of Bonus Pay from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @param  a_earnings_map
 * @return the amount of Bonus Pay, or zero if not found
 */
   public
   BigDecimal
   getBonusPay( Payroll_ResultType    a_result,
                Map< String, String > a_earnings_map )
   {
      return getPayForCategory(
                    a_result,
                    a_earnings_map,
                    WorkdayConstants.EARNING_TYPE_BONUS );
   }
   
/******************************************************************************
 * Gets the amount of Severance Pay from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @param  a_earnings_map
 * @return the amount of Severance Pay, or zero if not found
 */
   public
   BigDecimal
   getSeverancePay( Payroll_ResultType    a_result,
                    Map< String, String > a_earnings_map )
   {
      return getPayForCategory(
                    a_result,
                    a_earnings_map,
                    WorkdayConstants.EARNING_TYPE_SEVERANCE );
   }
   
/******************************************************************************
 * Gets the amount of Pension Pay from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @param  a_earnings_map
 * @return the amount of Pension Pay, or zero if not found
 */
   public
   BigDecimal
   getPensionPay( Payroll_ResultType    a_result,
                  Map< String, String > a_earnings_map )
   {
      return getPayForCategory(
                    a_result,
                    a_earnings_map,
                    WorkdayConstants.EARNING_TYPE_PENSION );
   }
   
/******************************************************************************
 * Gets the amount of Wage In Lieu Of Notice Pay from the Payroll_ResultType
 * object.
 * 
 * @param  a_result
 * @param  a_earnings_map
 * @return the amount of Wage In Lieu Of Notice Pay, or zero if not found
 */
   public
   BigDecimal
   getWageInLieuOfNoticePay( Payroll_ResultType    a_result,
                             Map< String, String > a_earnings_map )
   {
      return getPayForCategory(
                    a_result,
                    a_earnings_map,
                    WorkdayConstants.EARNING_TYPE_WAGE_IN_LIEU_OF_NOTICE );
   }
   
/******************************************************************************
 * Gets the worker's employee id from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @return the employee id, or null if not found
 */
   public
   String
   getEmployeeId( Payroll_BalanceType a_result )
   {
      if( a_result != null
            &&
          a_result.getWorker_Reference() != null )
      {
         for( WorkerObjectIDType l_id
               : a_result.getWorker_Reference().getID() )
         {
            if( l_id != null
                  &&
                EmployeeSyncConstants.WORKDAY_ENUM_EMPLOYEE_ID
                  .equals( l_id.getType() ) )
            {
               return l_id.get_value();
            }
         }
      }
         
      return null;
   }

/******************************************************************************
 * Gets the amount from the Payroll_BalanceType object for the deduction.
 * 
 * @param  a_result
 * @return the deduction amount, or null if not found
 */
   public
   BigDecimal
   getDeductionAmount( Payroll_BalanceType a_result )
   {
            
      if( a_result != null ) 
      {
         for( Pay_Calculation_Balance_DataType l_balance
               : a_result.getPay_Calculation_Balance_Data() )
         {
            if( l_balance != null )
            {
               for( Balance_Period_Data_For_Get_Payroll_BalancesType l_ref
                     : l_balance.getBalance_Period_Data() )
               {
                  if( l_ref != null )
                  {
                     for( Balance_Period_Detail_DataType  l_details
                           : l_ref.getBalance_Period_Detail_Data() )
                     {
                        if( l_details != null )
                        {  
                           return l_details.getBalance_Amount();
                        }  
                     }
                  }
               }
            }
         }
      }
      return null;
   }

/******************************************************************************
 * Gets the amount of pay of the given type from the Payroll_ResultType object.
 * 
 * @param  a_result
 * @param  a_earnings_map
 * @param  a_earnings_type
 * @return the amount of Regular Pay, or zero if not found
 */
   private
   BigDecimal
   getPayForCategory( Payroll_ResultType     a_result,
                      Map< String, String >  a_earnings_map,
                      String                 a_earnings_type )
   {
      BigDecimal              l_amount    = BigDecimal.ZERO;
      Earning_ReferenceType   l_earning   = null;
      
      if( a_result != null
             &&
          a_earnings_map != null
             &&
          a_earnings_type != null )
      {
         for( Result_Line_Details_For_Get_Payroll_ResultsType l_details
                 : a_result.getPayroll_Result_Data() )
         {
            if( l_details != null )
            {
               for( Pay_Component_ReferenceType l_ref
                       : l_details.getPay_Component_Reference() )
               {
                  if( l_ref != null )
                  {
                     l_earning = l_ref.getEarning();
                     if( l_earning != null
                            &&
                         a_earnings_type.equalsIgnoreCase(
                               a_earnings_map.get( l_earning.getCode() ) ) )
                     {
                        l_amount
                           = l_amount.add( l_details.getResult_Line_Amount() );
                     }
                  }
               }
            }
         }
      }
      
      return l_amount;
   }
}
