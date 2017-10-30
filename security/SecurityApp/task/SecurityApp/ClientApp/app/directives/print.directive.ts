import {Directive, Input, ElementRef, Renderer} from "@angular/core";
/**
 * Add the template content to the DOM unless the condition is true.
 */
@Directive({selector: '[zPrint]'})
export class ZPrint {

  constructor(private el: ElementRef, private renderer: Renderer) {
  }

  @Input() set zPrint(rawHtml: string) {
    console.log(this.el.nativeElement);
    let newElem = document.createElement("span");
    this.el.nativeElement.parentNode.appendChild(newElem);
    newElem.innerHTML = rawHtml;
  }
}
