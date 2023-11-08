import { Fundraise } from "./fundraise";

export interface Employee {
  id: number;
  name: string;
  imageUrl: string;
  phone: string;
  email: string;
  jobTitle: string;
  employeeCode: string;
  salary: number;
  assignedFundraise: Fundraise;
}