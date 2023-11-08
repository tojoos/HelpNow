import { User } from "./user";

export interface Announcement {
  id: number;
  name: string;
  imageUrl: string;
  title: string;
  description: string;
  author: User;
  status: string;
  creationDateTime: Date;
}