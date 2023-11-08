import { Fundraise } from "./fundraise";

export interface Organization {
  id: number;
  name: string;
  imageUrl: string;
  description: string;
  createdFundraises: Fundraise[];
}