export interface Employee {
  links: EntityRestEndpointUrl;
  firstName: string;
  lastName: string;
  title: string;
}

interface EntityRestEndpointUrl {
  self: { href: string; };
}
