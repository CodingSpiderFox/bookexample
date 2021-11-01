import dayjs from 'dayjs';

export interface IAuthor {
  id?: number;
  firstName?: string;
  lastName?: string;
  birthTimestamp?: string | null;
}

export const defaultValue: Readonly<IAuthor> = {};
