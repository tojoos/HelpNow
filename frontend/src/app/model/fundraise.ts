import { Employee } from "./employee";
import { Organization } from "./organization";

export interface Fundraise {
  id: number;
  name: string;
  imageUrl: string;
  description: string;
  requiredAmount: number;
  raisedAmount: number;
  startingDate: Date;
  endingDate: Date;
  assignedEmployees: Employee[];
  organization: Organization;
}