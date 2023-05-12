import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UploadResult } from 'src/app/models';
import { UploadService } from 'src/app/services/upload.service';

@Component({
  selector: 'app-view1',
  templateUrl: './view1.component.html',
  styleUrls: ['./view1.component.css']
})
export class View1Component implements OnInit {
 
  form!: FormGroup
  name!: string
  title!: string
  comments!: string
  archive!: File
  result: UploadResult[] = []
  

  constructor(private fb: FormBuilder, private router: Router, private uploadSvc: UploadService) { } 

  createForm(): FormGroup{
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
      title: this.fb.control<string>('', [Validators.required]),
      comments: this.fb.control<string>(''),
      archive: ['', Validators.required]
    })

  }

  ngOnInit(): void {
    this.form = this.createForm()
  }

  back(){
      this.router.navigate(['/']); ///back view0
  }

  onFileSelected(event: Event): void{
    const target = event.target as HTMLInputElement;
    if(target.files){
      const file = target.files[0];
      this.form.get('archive')?.setValue(file);
    }
    
  }

  onSubmit(){
    const result: UploadResult = {
      name: this.form.value['name'],
      title: this.form.value['title'],
      comments: this.form.value['comments'],
      archive: this.form.value['archive']
    }

    this.uploadSvc.upload(result) 
  }

}
